CREATE TABLE dbs_sys.EMPLOYEES (
   EmployeeId      INT        PRIMARY KEY     NOT NULL,
   first_name           TEXT    NOT NULL,
   last_name            TEXT     NOT NULL,
   Email                TEXT,
   Salary               REAL,
   Doj                  date, 
   CREATE_DATE          date,
   CREATED_BY           TEXT,
   UPDATED_DATE          date,
   UPDATED_BY           TEXT
);
insert into dbs_sys.EMPLOYEES (EmployeeId,first_name,last_name,Email,Salary,Doj,CREATE_DATE,
							  CREATED_BY) values
		(1,'ram','kalla','ramkalla@gmail.com',10000,date(now())-89,date(now()),'Admin'),			  
		(2,'raj','put','rajput@gmail.com',60000,date(now())-89,date(now()),'Admin'),
		(3,'rama','raj','ramaraj@gmail.com',20000,date(now())-23,date(now()),'Admin'),
		(4,'rajampuli','puli','rajpuli@gmail.com',67000,date(now())-22,date(now()),'Admin'),
		(5,'rakesh','kumar','rakesh@gmail.com',34567,date(now())-56,date(now()),'Admin');
 
 
CREATE TABLE dbs_sys.EMPLOYEE_PHONES
(
    id INT PRIMARY KEY NOT NULL,
    EmployeeId INT
        REFERENCES dbs_sys.EMPLOYEES (EmployeeId),
    PHONE_NUMBER TEXT NOT NULL,
    CREATE_DATE date,
    CREATED_BY TEXT,
    UPDATED_DATE date,
    UPDATED_BY TEXT
);
 
insert into dbs_sys.EMPLOYEE_PHONES (id,EmployeeId,PHONE_NUMBER,CREATE_DATE,CREATED_BY) values
		(1,1,987654321,date(now()),'Admin'),
		(6,1,987878787,date(now()),'Admin'),
		(2,2,988776655,date(now()),'Admin'),
		(3,3,934567894,date(now()),'Admin'),
		(4,4,986426272,date(now()),'Admin'),
		(5,5,937353726,date(now()),'Admin');