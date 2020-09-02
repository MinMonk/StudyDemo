package com.monk.demo;

import java.util.concurrent.ExecutionException;

import org.raml.v2.api.RamlModelBuilder;
import org.raml.v2.api.RamlModelResult;
import org.raml.v2.api.model.common.ValidationResult;
import org.raml.v2.api.model.v10.api.Api;

import com.alibaba.fastjson.JSON;

import amf.client.model.domain.CustomDomainProperty;
import amf.client.model.domain.EndPoint;
import amf.client.model.domain.NodeShape;
import amf.client.model.domain.OAuth1Settings;
import amf.client.model.domain.ObjectNode;
import amf.client.model.domain.Operation;
import amf.client.model.domain.ParametrizedResourceType;
import amf.client.model.domain.ParametrizedTrait;
import amf.client.model.domain.Payload;
import amf.client.model.domain.Request;
import amf.client.model.domain.ResourceType;
import amf.client.model.domain.ScalarNode;
import amf.client.model.domain.ScalarShape;
import amf.client.model.domain.SecurityScheme;
import amf.client.model.domain.Trait;
import amf.client.model.domain.WebApi;
import webapi.Raml10;
import webapi.WebApiBaseUnit;
import webapi.WebApiDocument;

public class Raml2JavaDemo {
	public static void main(String[] args) throws Exception {

		// resolveRaml();

		// parseFile();
		
		// generateFile();
		
		navigateApi();
	}
	
	public static void navigateApi() throws InterruptedException, ExecutionException {
	    // Parse RAML 1.0 file
	    String inp = "file://F:/gitee/webapi-parser/examples/api-specs/raml/navigation-example-api.raml";
	    WebApiDocument model = (WebApiDocument) Raml10.parse(inp).get();
	 // Access RAML 1.0 API
	    WebApi api = (WebApi) model.encodes();
	    
	    System.out.println("-----");
	    System.out.println(JSON.toJSONString(api));
	    System.out.println("-----");
	    // API root properties
	    System.out.println("Title: " + api.name().value());
	    System.out.println("Description: " + api.description().value());
	    System.out.println("First doc title: " + api.documentations().get(0).title().value());
	    System.out.println("First doc content: " + api.documentations().get(0).description().value());
	    System.out.println("Version: " + api.version().value());
	    System.out.println("First protocol: " + api.schemes().get(0).value());
	    System.out.println("Base uri: " + api.servers().get(0).url().value());

	    // Access security scheme from root
	    System.out.println(
	      "First security scheme name: " +
	      api.security().get(0).schemes().get(0).scheme().name().value());
	    System.out.println(
	      "First security scheme description: " +
	      api.security().get(0).schemes().get(0).scheme().description().value());

	    // Endpoint /users
	    EndPoint users = (EndPoint) api.endPoints().get(0);
	    System.out.println("Path: " + users.path().value());
	    System.out.println("Relative path: " + users.relativePath());
	    ParametrizedResourceType appliedRt = (ParametrizedResourceType) users.extendsNode().get(0);
	    System.out.println("Resource type: " + appliedRt.name().value());

	    // Access resource type properties from endpoint
	    ObjectNode referencedRf = (ObjectNode) ((ResourceType) appliedRt.target().linkTarget().get()).dataNode();
	    ObjectNode csrfGet = (ObjectNode) referencedRf.properties().get("post");
	    ObjectNode csrfQParams = (ObjectNode) csrfGet.properties().get("queryParameters");
	    ObjectNode csrfItself = (ObjectNode) csrfQParams.properties().get("csrf");
	    ScalarNode csrfTypeProp = (ScalarNode) csrfItself.properties().get("type");
	    System.out.println("Page query param `csrf` type: " + csrfTypeProp.value().value());

	    // POST /users
	    Operation postUsers = (Operation) users.operations().get(0);

	    // Access trait properties from endpoint
	    ParametrizedTrait appliedTrait = (ParametrizedTrait) postUsers.extendsNode().get(0);
	    System.out.println("Trait: " + appliedTrait.name().value());
	    ObjectNode refTrait = (ObjectNode) ((Trait) appliedTrait.target().linkTarget().get()).dataNode();
	    ObjectNode trHeaders = (ObjectNode) refTrait.properties().get("headers");
	    ObjectNode trXTracker = (ObjectNode) trHeaders.properties().get("X-Tracker");
	    ScalarNode trHeaderDesc = (ScalarNode) trXTracker.properties().get("description");
	    System.out.println("Trait header desc: " + trHeaderDesc.value().value());

	    // POST /users request payload
	    Payload postUsersReq = (Payload) ((Request) postUsers.request()).payloads().get(0);
	    System.out.println("Request media type: " + postUsersReq.mediaType().value());

	    // Access User type from request
	    NodeShape postUsersType = (NodeShape) postUsersReq.schema().inherits().get(0).linkTarget().get();
	    System.out.println("Type name: " + postUsersType.name().value());
	    System.out.println("Second property name: " + postUsersType.properties().get(1).name().value());
	    System.out.println(
	      "Second property type: " +
	      ((ScalarShape) postUsersType.properties().get(1).range()).dataType().value());

	    // Endpoint /users/{id}
	    EndPoint user = (EndPoint) api.endPoints().get(1);
	    System.out.println("Path: " + user.path().value());
	    System.out.println("Relative path: " + user.relativePath());
	    System.out.println(
	      "First annotation name: " +
	      user.customDomainProperties().get(0).name().value());
	    System.out.println(
	      "First annotation type: " +
	      ((ScalarNode) user.customDomainProperties().get(0).extension()).dataType().value());
	    System.out.println(
	      "First annotation value: " +
	      ((ScalarNode) user.customDomainProperties().get(0).extension()).value());


	    // GET /users/{id}
	    Operation getUser = (Operation) user.operations().get(0);
	    System.out.println("Status code: " + getUser.responses().get(0).statusCode().value());
	    Payload getUserResp = (Payload) getUser.responses().get(0).payloads().get(0);
	    System.out.println("Response media type: " + getUserResp.mediaType().value());

	    // Access User type from response
	    NodeShape getUsersType = (NodeShape) getUserResp.schema().inherits().get(0).linkTarget().get();
	    System.out.println("Type name: " + getUsersType.name().value());
	    System.out.println("First property name: " + getUsersType.properties().get(0).name().value());
	    System.out.println(
	      "First property type: " +
	      ((ScalarShape) postUsersType.properties().get(0).range()).dataType().value());

	    // Annotation 'experimental'
	    CustomDomainProperty annotation = (CustomDomainProperty) model.declares().get(0);
	    System.out.println("Annotation name: " + annotation.name().value());
	    System.out.println("Annotation type: " + ((ScalarShape) annotation.schema()).dataType().value());

	    // Type 'User'
	    NodeShape userType = (NodeShape) model.declares().get(1);
	    System.out.println("User type properties:");
	    for (int i = 0; i < userType.properties().size(); i++) {
	      System.out.println(
	        userType.properties().get(i).name().value() + ": " +
	        ((ScalarShape) userType.properties().get(i).range()).dataType().value()
	      );
	    }
	    ScalarShape age = (ScalarShape) userType.properties().get(2).range();
	    System.out.println("Age from " + age.minimum().value() + " to " + age.maximum().value());

	    // ResourceType 'postable'
	    ResourceType postable = (ResourceType) model.declares().get(2);
	    ObjectNode postableNode = (ObjectNode) postable.dataNode();
	    ObjectNode postableGet = (ObjectNode) postableNode.properties().get("post");
	    ObjectNode pgQparams = (ObjectNode) postableGet.properties().get("queryParameters");
	    ObjectNode postableCsrtItself = (ObjectNode) pgQparams.properties().get("csrf");
	    ScalarNode postableCsrfTypeProp = (ScalarNode) postableCsrtItself.properties().get("type");
	    System.out.println("Page query param `csrf` type: " + postableCsrfTypeProp.value().value());

	    // Trait 'traceable'
	    Trait traceable = (Trait) model.declares().get(3);
	    ObjectNode traceableNode = (ObjectNode) traceable.dataNode();
	    ObjectNode traceHeaders = (ObjectNode) traceableNode.properties().get("headers");
	    ObjectNode traceXTracker = (ObjectNode) traceHeaders.properties().get("X-Tracker");
	    ScalarNode traceHeaderDesc = (ScalarNode) traceXTracker.properties().get("description");
	    System.out.println("Traceable header desc: " + traceHeaderDesc.value().value());
	    ScalarNode traceHeaderPattern = (ScalarNode) traceXTracker.properties().get("pattern");
	    System.out.println("Traceable header pattern: " + traceHeaderPattern.value().value());
	    ScalarNode traceHeaderExample = (ScalarNode) traceXTracker.properties().get("example");
	    System.out.println("Traceable header example: " + traceHeaderExample.value().value());

	    // SecurityScheme oauth_1_0
	    SecurityScheme oauth1 = (SecurityScheme) model.declares().get(4);
	    System.out.println("Description: " + oauth1.description().value());
	    System.out.println("Type: " + oauth1.type().value());
	    OAuth1Settings settings = (OAuth1Settings) oauth1.settings();
	    System.out.println("requestTokenUri: " + settings.requestTokenUri().value());
	    System.out.println("authorizationUri: " + settings.authorizationUri().value());
	    System.out.println("tokenCredentialsUri: " + settings.tokenCredentialsUri().value());
	}
	

	private static void resolveRaml() {
		RamlModelResult ramlModelResult = new RamlModelBuilder().buildApi("D:\\testDir\\raml\\demo.raml");
		if (ramlModelResult.hasErrors()) {
			for (ValidationResult validationResult : ramlModelResult.getValidationResults()) {
				System.out.println(validationResult.getMessage());
			}
		} else {
			Api api = ramlModelResult.getApiV10();
			System.out.println(api.baseUri().toString());

		}
	}

	public static void parseFile() throws InterruptedException, ExecutionException {
		// Parse the file
		final WebApiBaseUnit result = Raml10.parse("file://D:/testDir/raml/demo.raml").get();

		// Log parsed model API
		System.out.println("Parsed Raml10 file. Expected unit encoding webapi: " + ((WebApiDocument) result).encodes());
	}

	// Example of generating RAML 1.0 file
	public static void generateFile() throws InterruptedException, ExecutionException {
		// Parse RAML 1.0 file to get WebApi Model
		final WebApiBaseUnit result = Raml10.parse("file://D:/testDir/raml/demo.raml").get();

		// Resolve parsed model (optional)
		final WebApiBaseUnit resolved = Raml10.resolve(result).get();

		String fpath = "file://D:/testDir/generated.raml";
		// Generate RAML 1.0 file
		Raml10.generateFile(resolved, fpath).get();
		System.out.println("Generating Raml10 file at: " + fpath);
	}
}
