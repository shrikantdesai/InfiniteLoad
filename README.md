# InfiniteLoad
Infinite load mongoDB spring project

1. Create Mongo instance 
http://docs.mongodb.org/manual/installation/
2. Import project as maven project 
3. Edit mongo Setting in src/main/webapp/WEB-INF/dispatcher-servlet.xml
4.build project run on tomcat server.
5.Sample data is present in path,src/main/resources/data.csv. load data by calling rest call /mobilityApp/rest/create
6. Check all Recodrs loaded using, /mobilityApp/rest/details/all ( Dont use when count is really big)
7. Other rest calls for paging retrival,  /mobilityApp/rest/details/{page}/{count}
8. Data clean up , not recommended to use just created for easy data clean up. /mobilityApp/rest/clean
9. Home page /mobilityApp


  



