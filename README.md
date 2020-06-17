# assignmentTechnology stack
1. Java11
2. Springboot
3. H2 database
4. Junit
5. Capable to build docker image(dockerfile provided)
 
 API provided
 
1. /tradeEntry to insert tradeRecords

2. /all to retrieve all records
 
 
 
Execution steps

step-1. Run the application using TradestoreApplication.java executable class.

step-2. use postman to insert few records by below steps

URL:localhost:8080/tradeEntry/

Method: post

	body:  {
			"tradeID":"T1",
			"version":"1",
			"counterPartyID":"CP-1",
			"bookID":"B1",
			"maturityDate":"15/06/2021",
			"expired":"N"
		}

insert next record as below

			{
			"tradeID":"T1",
			"version":"2",
			"counterPartyID":"CP-2",
			"bookID":"B2",
			"maturityDate":"15/06/2021",
			"expired":"N"
		}
		
insert next record as below

			{
			"tradeID":"T1",
			"version":"3",
			"counterPartyID":"CP-3",
			"bookID":"B3",
			"maturityDate":"15/06/2021",
			"expired":"N"
		}

insert next record as below

			{
			"tradeID":"T2",
			"version":"1",
			"counterPartyID":"CP-1",
			"bookID":"B1",
			"maturityDate":"15/06/2021",
			"expired":"N"
		}
		
 step-3 to check records into database execute below URL in postman

 URL: http://localhost:8080/all


 method:get

 results should be looks like below

	[
		{
			"id": 1,
			"tradeID": "T1",
			"version": "1",
			"counterPartyID": "CP-1",
			"bookID": "B1",
			"maturityDate": "20/06/2021",
			"createdDate": "17/06/2020",
			"expired": "N"
		},
		{
			"id": 2,
			"tradeID": "T1",
			"version": "2",
			"counterPartyID": "CP-2",
			"bookID": "B2",
			"maturityDate": "20/06/2021",
			"createdDate": "17/06/2020",
			"expired": "N"
		},
		{
			"id": 3,
			"tradeID": "T1",
			"version": "3",
			"counterPartyID": "CP-3",
			"bookID": "B3",
			"maturityDate": "20/06/2021",
			"createdDate": "17/06/2020",
			"expired": "N"
		}
	]

Also we can check data into H2 database by below process

URL: http://localhost:8080/h2

table: TRADE_STORE


Step-4 Validating given scenario:

scenario-1: trying to insert trade data with lower maturityDate.

	{
	"tradeID":"T1",
	"version":"3",
	"counterPartyID":"CP-3",
	"bookID":"B3",
	"maturityDate":"15/06/2019",

	"expired":"N"

	}

it should give below results

	{
		"status": "failure",
		"code": "fail_102",
		"description": "Maturity date less then todays date"
	}

scenario-2: trying to insert trade data with same version that available in database, in this case database should be updated with latest trade record.

	{
	"tradeID":"T1",
	"version":"3",
	"counterPartyID":"CP-4",
	"bookID":"B4",
	"maturityDate":"15/06/2021",

	"expired":"N"

	}

it should give below results

	{
		"status": "success",
		"code": "suc_200",
		"description": "Successfully save/update trade"
	}

scenario-3: trying to insert trade data with higher version that available in database, in this case trade data should be inserted..

	{
	"tradeID":"T1",
	"version":"4",
	"counterPartyID":"CP-4",
	"bookID":"B4",
	"maturityDate":"15/06/2021",

	"expired":"N"

	}

it should give below results

	{
		"status": "success",
		"code": "suc_200",
		"description": "Successfully save/update trade"
	}

scenario-3: trying to insert trade data with lower version that available in database, in this case system will give error like lower version cant updated.

	{
	"tradeID":"T1",
	"version":"3",
	"counterPartyID":"CP-3",
	"bookID":"B3",
	"maturityDate":"15/06/2021",

	"expired":"N"

	}

it should give below results

	{
		"status": "failure",
		"code": "fail_101",
		"description": "Fail to save into database due to lower version"
	}


Step-5  Scheduling job to check maturity expiry date

Scheduller class is running and checking in every 50 second for testing but its configurable by properties file to update Scheduling interval.

Step-6 logs file also creating in root folder to check logs.

Step-7 Test cases provided for every scenario and every class.

