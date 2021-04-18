# reqres_api_automation
API Automation for reqres application

# Execution of all test through command
>> ./gradlew test

## Login manual test scenarios

Scenario 1: Registered user tries to login with correct username and correct password
Expected: Status code as 200 and token is not null.

Scenario 2: Registered user tries to login with incorrect username and correct password.
Expected: status code as 4XX and error as incorrect username.

Scenario 3: Registered user tries to login with correct username and incorrect password.
Expected: status code as 4XX and error as "incorrect password."

Scenario 4: Registered user tries to login with incorrect username and incorrect password.
Expected: status code as 4XX and error as "incorrect username or password."

Scenario 5: Registered user tries to login with no username and correct password.
Expected: status code as 400 and error as "missing username".

Scenario 6: Registered user tries to login with correct username and no password.
Expected: status code as 400 and error as "missing password".

Scenario 7: Registered user tries to login without entering username and password.
Expected: status code as 400 and error as "missing username and password".

Scenario 8: Registered user login with incorrect format of email.
Expected: status code as 4XX and error as incorrect username format.

Scenario 9: Performance check for login API


