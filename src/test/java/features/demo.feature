@basic
Feature: Basic test
 
	@form_test_success
	Scenario Outline: form success
		Given the application <url>
		When I fill the form with <name>, <email>, <subject>, <message>
		And click submit button
		Then shows a success message
 		
	Examples:
	| url | name  | email 			| subject | message |
    | 192.168.0.102 | dummy| mail@mail.com | dummy   | message |