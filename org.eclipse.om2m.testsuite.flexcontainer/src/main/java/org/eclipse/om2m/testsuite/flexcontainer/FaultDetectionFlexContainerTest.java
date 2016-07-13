package org.eclipse.om2m.testsuite.flexcontainer;

import org.eclipse.om2m.commons.constants.Constants;
import org.eclipse.om2m.commons.constants.ResponseStatusCode;
import org.eclipse.om2m.commons.resource.CustomAttribute;
import org.eclipse.om2m.commons.resource.FlexContainer;
import org.eclipse.om2m.commons.resource.ResponsePrimitive;
import org.eclipse.om2m.core.service.CseService;
import org.eclipse.om2m.testsuite.flexcontainer.TestReport.Status;

public class FaultDetectionFlexContainerTest extends FlexContainerTestSuite {

	public FaultDetectionFlexContainerTest(final CseService pCseService) {
		super(pCseService);
	}

	@Override
	protected String getTestSuiteName() {
		return "FaultDetectionFlexContainerTest";
	}

	/**
	 * Test create and retrieve FaultDetection FlexContainer
	 */
	public void testCreateFaultDetectionFlexContainer() {

		String baseLocation = "/" + Constants.CSE_ID + "/" + Constants.CSE_NAME;
		String flexContainerName = "FaultDetectionFlexContainer_" + System.currentTimeMillis();
		String flexContainerLocation = baseLocation + "/" + flexContainerName;

		FlexContainer flexContainer = new FlexContainer();
		flexContainer.setContainerDefinition("org.onem2m.home.moduleclass.faultdetection");
		flexContainer.setOntologyRef("OrangeOntology");
		flexContainer.setCreator("Greg");

		CustomAttribute statusCustomAttribute = new CustomAttribute();
		statusCustomAttribute.setCustomAttributeName("status");
		statusCustomAttribute.setCustomAttributeType("xs:boolean");
		statusCustomAttribute.setCustomAttributeValue("false");
		flexContainer.getCustomAttributes().add(statusCustomAttribute);

		CustomAttribute codeCustomAttribute = new CustomAttribute();
		codeCustomAttribute.setCustomAttributeName("code");
		codeCustomAttribute.setCustomAttributeType("xs:integer");
		codeCustomAttribute.setCustomAttributeValue("123");
		flexContainer.getCustomAttributes().add(codeCustomAttribute);

		CustomAttribute descriptionCustomAttribute = new CustomAttribute();
		descriptionCustomAttribute.setCustomAttributeName("description");
		descriptionCustomAttribute.setCustomAttributeType("xs:string");
		descriptionCustomAttribute.setCustomAttributeValue("My description");
		flexContainer.getCustomAttributes().add(descriptionCustomAttribute);

		// send create Request
		ResponsePrimitive response = sendCreateFlexContainerRequest(flexContainer, baseLocation, flexContainerName);
		FlexContainer createdFlexContainer = null;
		if (!response.getResponseStatusCode().equals(ResponseStatusCode.CREATED)) {
			// KO
			createTestReport("testCreateFaultDetectionFlexContainer", Status.KO,
					"unable to create FaultDetectionFlexContainer", null);
			return;
		} else {
			createdFlexContainer = (FlexContainer) response.getContent();

			if (!flexContainerName.equals(createdFlexContainer.getName())) {
				createTestReport("testCreateFaultDetectionFlexContainer", Status.KO,
						"resource name are differents(expected:" + flexContainerName + ", found:"
								+ createdFlexContainer.getName() + ")",
						null);
				return;
			}

			try {
				checkFlexContainerCustomAttribute(flexContainer, createdFlexContainer);
			} catch (Exception e) {
				createTestReport("testCreateFaultDetectionFlexContainer", Status.KO,
						"custom attributes are differents(expected:" + flexContainer.getCustomAttributes() + ", found:"
								+ createdFlexContainer.getCustomAttributes() + ")",
						e);
				return;
			}

			try {
				checkFlexContainerCreator(flexContainer, createdFlexContainer);
			} catch (Exception e) {
				createTestReport("testCreateFaultDetectionFlexContainer", Status.KO, "creator are differents(expected:"
						+ flexContainer.getCreator() + ", found:" + createdFlexContainer.getCreator() + ")", e);
				return;
			}

			try {
				checkFlexContainerDefinition(flexContainer, createdFlexContainer);
			} catch (Exception e) {
				createTestReport("testCreateFaultDetectionFlexContainer", Status.KO,
						"containerDefinition are differents(expected:" + flexContainer.getContainerDefinition()
								+ ", found:" + createdFlexContainer.getContainerDefinition() + ")",
						e);
				return;
			}

			try {
				checkFlexContainerOntologyRef(flexContainer, createdFlexContainer);
			} catch (Exception e) {
				createTestReport("testCreateFaultDetectionFlexContainer", Status.KO,
						"ontologyRef are differents(expected:" + flexContainer.getOntologyRef() + ", found:"
								+ createdFlexContainer.getOntologyRef() + ")",
						e);
				return;
			}

		}

		// try to retrieve the FlexContainer
		response = sendRetrieveRequest(flexContainerLocation);
		if (!response.getResponseStatusCode().equals(ResponseStatusCode.OK)) {
			// KO
			createTestReport("testCreateFaultDetectionFlexContainer", Status.KO, "unable to retrieve the FlexContainer",
					null);
			return;
		} else {
			FlexContainer retrievedFlexContainer = (FlexContainer) response.getContent();
			try {
				checkFlexContainer(createdFlexContainer, retrievedFlexContainer);
			} catch (Exception e) {
				createTestReport("testCreateFaultDetectionFlexContainer", Status.KO,
						"flexContainers are differents: " + e.getMessage(), e);
				return;
			}
		}

		createTestReport("testCreateFaultDetectionFlexContainer", Status.OK, null, null);

	}

	public void testUpdateFaultDetectionFlexContainer() {

		String baseLocation = "/" + Constants.CSE_ID + "/" + Constants.CSE_NAME;
		String flexContainerName = "FaultDetectionFlexContainer_" + System.currentTimeMillis();
		String flexContainerLocation = baseLocation + "/" + flexContainerName;

		FlexContainer flexContainer = new FlexContainer();
		flexContainer.setContainerDefinition("org.onem2m.home.moduleclass.faultdetection");
		flexContainer.setOntologyRef("OrangeOntology");
		flexContainer.setCreator("Greg");

		CustomAttribute statusCustomAttribute = new CustomAttribute();
		statusCustomAttribute.setCustomAttributeName("status");
		statusCustomAttribute.setCustomAttributeType("xs:boolean");
		statusCustomAttribute.setCustomAttributeValue("false");
		flexContainer.getCustomAttributes().add(statusCustomAttribute);

		CustomAttribute codeCustomAttribute = new CustomAttribute();
		codeCustomAttribute.setCustomAttributeName("code");
		codeCustomAttribute.setCustomAttributeType("xs:integer");
		codeCustomAttribute.setCustomAttributeValue("123");
		flexContainer.getCustomAttributes().add(codeCustomAttribute);

		CustomAttribute descriptionCustomAttribute = new CustomAttribute();
		descriptionCustomAttribute.setCustomAttributeName("description");
		descriptionCustomAttribute.setCustomAttributeType("xs:string");
		descriptionCustomAttribute.setCustomAttributeValue("My description");
		flexContainer.getCustomAttributes().add(descriptionCustomAttribute);

		// send create Request
		ResponsePrimitive response = sendCreateFlexContainerRequest(flexContainer, baseLocation, flexContainerName);
		if (!response.getResponseStatusCode().equals(ResponseStatusCode.CREATED)) {
			// KO
			createTestReport("testUpdateFaultDetectionFlexContainer", Status.KO,
					"unable to create FaultDetectionFlexContainer", null);
			return;
		}

		// update the status value
		FlexContainer flexContainerToBeUpdated = new FlexContainer();
		flexContainerToBeUpdated.setContainerDefinition("org.onem2m.home.moduleclass.faultdetection");
		CustomAttribute statusCustomAttributeToBeUpdated = new CustomAttribute();
		statusCustomAttributeToBeUpdated.setCustomAttributeName("status");
		statusCustomAttributeToBeUpdated.setCustomAttributeType("xs:boolean");
		statusCustomAttributeToBeUpdated.setCustomAttributeValue("true");
		flexContainerToBeUpdated.getCustomAttributes().add(statusCustomAttributeToBeUpdated);

		// send UPDATE request
		response = sendUpdateFlexContainerRequest(flexContainerLocation, flexContainerToBeUpdated);
		FlexContainer updatedFlexContainer = null;
		if (!response.getResponseStatusCode().equals(ResponseStatusCode.UPDATED)) {
			// KO
			createTestReport("testUpdateFaultDetectionFlexContainer", Status.KO,
					"unable to update FaultDetectionFlexContainer", null);
			return;
		} else {
			updatedFlexContainer = (FlexContainer) response.getContent();
			if (!updatedFlexContainer.getCustomAttribute("status").getCustomAttributeValue().equals("true")) {
				createTestReport("testUpdateFaultDetectionFlexContainer", Status.KO,
						"expected \"true\" value for status custom attribute", null);
				return;
			}
		}

		createTestReport("testUpdateFaultDetectionFlexContainer", Status.OK, null, null);
	}

	public void testDeleteFaultDetectionFlexContainer() {
		String baseLocation = "/" + Constants.CSE_ID + "/" + Constants.CSE_NAME;
		String flexContainerName = "FaultDetectionFlexContainer_" + System.currentTimeMillis();
		String flexContainerLocation = baseLocation + "/" + flexContainerName;

		FlexContainer flexContainer = new FlexContainer();
		flexContainer.setContainerDefinition("org.onem2m.home.moduleclass.faultdetection");
		flexContainer.setOntologyRef("OrangeOntology");
		flexContainer.setCreator("Greg");

		CustomAttribute statusCustomAttribute = new CustomAttribute();
		statusCustomAttribute.setCustomAttributeName("status");
		statusCustomAttribute.setCustomAttributeType("xs:boolean");
		statusCustomAttribute.setCustomAttributeValue("false");
		flexContainer.getCustomAttributes().add(statusCustomAttribute);

		CustomAttribute codeCustomAttribute = new CustomAttribute();
		codeCustomAttribute.setCustomAttributeName("code");
		codeCustomAttribute.setCustomAttributeType("xs:integer");
		codeCustomAttribute.setCustomAttributeValue("123");
		flexContainer.getCustomAttributes().add(codeCustomAttribute);

		CustomAttribute descriptionCustomAttribute = new CustomAttribute();
		descriptionCustomAttribute.setCustomAttributeName("description");
		descriptionCustomAttribute.setCustomAttributeType("xs:string");
		descriptionCustomAttribute.setCustomAttributeValue("My description");
		flexContainer.getCustomAttributes().add(descriptionCustomAttribute);

		// send create Request
		ResponsePrimitive response = sendCreateFlexContainerRequest(flexContainer, baseLocation, flexContainerName);
		if (!response.getResponseStatusCode().equals(ResponseStatusCode.CREATED)) {
			// KO
			createTestReport("testDeleteFaultDetectionFlexContainer", Status.KO,
					"unable to create FaultDetectionFlexContainer", null);
			return;
		}

		// retrieve it ==> OK
		response = sendRetrieveRequest(flexContainerLocation);
		if (!response.getResponseStatusCode().equals(ResponseStatusCode.OK)) {
			createTestReport("testDeleteFaultDetectionFlexContainer", Status.KO,
					"unable to retrieve FaultDetectionFlexContainer", null);
			return;
		}

		// delete it
		response = sendDeleteRequest(flexContainerLocation);
		if (!response.getResponseStatusCode().equals(ResponseStatusCode.DELETED)) {
			// KO
			createTestReport("testDeleteFaultDetectionFlexContainer", Status.KO,
					"unable to delete FaultDetectionFlexContainer", null);
			return;
		}

		// retrieve it again ==> NOT FOUND
		response = sendRetrieveRequest(flexContainerLocation);
		if (!response.getResponseStatusCode().equals(ResponseStatusCode.NOT_FOUND)) {
			createTestReport("testDeleteFaultDetectionFlexContainer", Status.KO,
					"expected " + ResponseStatusCode.NOT_FOUND + ", found: " + response.getResponseStatusCode(), null);
			return;
		}

		createTestReport("testDeleteFaultDetectionFlexContainer", Status.OK, null, null);
	}

}
