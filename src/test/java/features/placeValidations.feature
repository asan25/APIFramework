Feature: Validating Place API's

  @AddPlace
  Scenario Outline: Verify if Place is being Successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>","<language>","<address>"
    When user calls "AddPlaceAPI" with "POST" http request
    Then the API call is success with status code 200
    And "status" is response body is "OK"
    And "scope" is response body is "APP"
    And verify place_id created maps to "<name>" using "getPlaceAPI"

    Examples:
    |name|language|address|
    |Con Brown|English|Roosevelt Ave London|
    |dddd|asdasdasd|asdasd|

    @DeletePlace
    Scenario: Verify if Delete Place functionality is working
      Given DeletePlace Payload
      When user calls "deletePlaceAPI" with "POST" http request
      Then the API call is success with status code 200
      And "status" is response body is "OK"

