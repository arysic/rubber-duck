# Run application
mvn clean spring-boot:run

# Sample requests
GET
* http://localhost:8090/api - lists all exchange rates
* http://localhost:8090/api/2020-03-04/PLN - gets exchange rates for a date (base currency - PLN; recalculating and converting to different base currencies not handled yet)

POST
* http://localhost:8090/api - inserts a set of rates for a date (if one doesn't already exist)
* request example: {
                      "date": "2020-03-11",
                      "base": "PLN",
                      "rates":    {
                         "EUR": 4.0011,
                         "USD": 5.5011
                      }
                   }

# ToDo next
There is still a lot to do, but the sample is good enough I guess. Nevertheless I will continue improving the project:
* write tests
* replace Date with LocalDate
* handle exceptions smart
* add functionalities
 