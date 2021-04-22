# Spanner Crud APIs

This application exposes one RESTful API ```/api/outbox/create```
which simply creates a new record in the Outbox table. 

Sample Payload as under:
```
{
	"locator":"AAA001",
	"version":1,
	"parent_locator":"",
   	"created": "2021-04-20T13:44:21.123Z",
	"data":"pnr:{}"	
}

```

# Execution steps

```
1. mvn clean install
2. mvn spring-boot:run
```

# Running on Gcloud App Engine

```
1. Please make sure app.yaml(present in this project) and spanner-crud.jar file are copied into a separate deployment folder. 
2. Move to the created deployment folder and fire the below command from Google cloud sdk shell. 
   
gcloud app deploy app.yaml
```

# GCloud Project and DB Configuration


```
project-id: pnr-order-poc
database: database-1
instance: instance-1
```


# Spanner Crud DDL Scripts

* Outbox table
```
CREATE TABLE OUTBOX (
	locator STRING(6) NOT NULL,
	version INT64 NOT NULL,
	parent_locator STRING(6),
	created TIMESTAMP NOT NULL,
	data STRING(MAX) NOT NULL,
) PRIMARY KEY (locator, version)

```
* Outbox_status table
```
CREATE TABLE OUTBOX_STATUS (
	locator STRING(6) NOT NULL,
	version INT64 NOT NULL,
	destination STRING(20) NOT NULL,
	status INT64 NOT NULL,
	created TIMESTAMP NOT NULL,
	updated TIMESTAMP NOT NULL,
	instance STRING(64) NOT NULL,
	FOREIGN KEY (locator, version) REFERENCES OUTBOX(locator, version),
) PRIMARY KEY (locator, version, destination),
INTERLEAVE IN PARENT OUTBOX ON DELETE CASCADE;

CREATE INDEX processing_locators 
ON OUTBOX_STATUS (
	status,
	locator
);
```
