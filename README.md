# Vault
## CNIT 355 project by REDACTED

This project is for the final project of the class CNIT 355 at Purdue University.

The objective of this project was to create a basic Password Storage application in Android. 

This application is targeted towards those who don't trust using services that store credentials on an external server, and as a result all information is stored locally on the phone.

An advantage of using an external service is that passwords can be accessed through an external website. However, a disadvantage is passing data to the Internet, as well as relying on an external companies service, is risky.

This application stores and encrypts information locally, meaning that the data is never being sent to the Internet. This also means that should access to the physical device go away, so would access to the passwords. As a result, the use case is for those seeking a more accessible and secure alternative to a physical notebook or journal to store passwords, and the target audience is those who distrust external parties. 

## Technical Specifications: 
1) The minimum API level required is API 27.
2) No external dependencies or libraries have been utilized 
3) AES is the encryption algorithm utilized
4) A "Credential Profile" is a Name, Username, Password, Email, and URL, along with a Color associated with the profile for increased memory recall
5) Credential Profiles are saved as JSON objects
6) Password generation occurs by using an imported dictionary of words and modifying and combining them to meet specifications. 
7) Dictionary is currently stored in words.json. Current dictionary is acceptable for a prototype. 
8) Passwords are currently stored in TotallyNotPasswords.json


## Features
1) Vault Home: Allows user access to the following. 
2) Store Password: 
Input a Credential Profile, allow random generation of password, and save Credential Profile. 
3) Generate New Password:
Separated random generation of passwords. User can specify end password length, and other aspects like including Capitals, Numbers, and Special Characters
4) Passwords: 
Edit and view saved credential profiles. 

Credential Profiles are securely encrypted when saved in Store Password, and securely decrypted when accessed through Passwords. 





## Ideas for future features:
*Due to lack of resources, the following features are "nice to have" features. While the app is still operational without them, having these be added would increase the quality of the application.*

1) Operational search function for looking up saved credential profiles
2) Have Credential profile colors be displayed when viewing / editing them 
3) Add categorization for types of credential profile
4) Utilize biometrics and require authentication before creating or accessing credential profile
5) Delete credential profiles
6) Expand dictionary used for password generation

