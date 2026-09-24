# journal-customer-management-ms
This micro service lets you insert, read, modify or delete customer data (e.g. name, birthday, address),
as well as information about their accounts in different websites (e.g. Gmail, Instagram, Stack Overflow).

It follows the **Customer Management API** (TMF629) from TM Forum, a global industry association
dedicated to the telecommunications and connectivity ecosystem.
They drive standardization, by creating common frameworks and data definitions to be adopted by the alliance of 800+ member organizations.
Along with their published Open APIs, these help promote seamless integration between different vendor systems.

## APIs
Name           | CRUD   | HTTP   | Path                               | Description                                                                 | Flow
---------------|--------|--------|------------------------------------|-----------------------------------------------------------------------------|-----
addCustomer    | CREATE | POST   | /customer                          | Creates a document in our database with the customer's information          | TBD
getCustomer    | READ   | GET    | /customer/{username}               | Retrieves the customer whose username equals the given one                  | TBD
checkUsername  | READ   | GET    | /username                          | Checks if a certain username is already in use                              | TBD
updateCustomer | UPDATE | PATCH  | /customer/{username}               | Updates any of the specified customer's information                         | TBD
updateUsername | UPDATE | PUT    | /customer/{username}               | Updates the customer's username                                             | TBD
deleteCustomer | DELETE | DELETE | /customer/{username}               | Removes the customer from our database, as well as all their accounts       | TBD
addAccounts    | CREATE | POST   | /customer/{username}/accounts      | Creates a document in our database with the different accounts' information | TBD
listAccounts   | READ   | GET    | /customer/{username}/accounts      | Lists the customer's accounts                                               | TBD
getAccount     | READ   | GET.   | /customer/{username}/accounts/{id} | Retrieves the account whose ID equals the given one                         | TBD
updateAccount  | UPDATE | PATCH  | /customer/{username}/accounts/{id} | Updates any of the specified account's information                          | TBD
deleteAccount  | DELETE | DELETE | /customer/{username}/accounts/{id} | Removes the account from our database                                       | TBD

**Obs.:** PUT implies the replacement of an entire resource, so we use it only for the *updateUsername* API,
since **username** is treated as the customer ID and changing it requires the deletion of a document and creation of another.
For the other *update* APIs, most customers choose to change only some of their information, so we use PATCH.

## Data Providers
Provider | Type           | Description                                                  | Use Cases
---------|----------------|--------------------------------------------------------------|--------------
MongoDB  | NoSQL Database | Stores all the customers' data and their respective accounts | {All of them}

### MongoDB
This non-relational database is used to store all the customers' data and their respective accounts.
It also encrypts any sensitive PII (Personal Identifiable Information) to keep the customers' safe in case any security breach happens.

Collection    | Field                  | Description                                                                   | Encrypted
--------------|------------------------|-------------------------------------------------------------------------------|----------
customer      | id                     | Unique username chosen by the customer and used to retrieve their data        | YES
customer      | href                   | Link that leads to the customer's details. This includes the getCustomer path | YES
customer      | role                   | Engaged party's role in the customer's life (usually their company/employer)  | NO
customer      | engagedParty.id        | Internal database ID for the engaged party                                    | NO
customer      | engagedParty.name      | Name of the engaged party                                                     | NO
customer      | engagedParty.href      | Link to the engaged party's website, if applicable                            | NO
customer      | validFor.endDateTime   | When the customer's profile should be deleted                                 | NO
contactMedium | preferred              | Whether the contact medium is the default one                                 | NO
contactMedium | contactType            | Type of contact medium (e.g. personal email, work email, mobile phone)        | NO
contactMedium | id                     | The contact medium itself                                                     | YES
contactMedium | validFor.startDateTime | When the contact medium can start being used                                  | NO
contactMedium | validFor.endDateTime   | When the contact medium can no longer be used                                 | NO
account       | id                     | Internal database ID for the account                                          | YES
account       | href                   | Link that leads to the account's details. This includes the getAccount path   | YES
account       | name                   | Name of the website on which the account was created by/for                   | NO

**REMEMBER TO ADD THE OTHER FIELDS**

**Obs.:** The fields chosen for encryption are also masked in all the logs.

## How To Run Locally
TBD
