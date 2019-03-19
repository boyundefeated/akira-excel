[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.boyundefeated/akira-excel/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.boyundefeated/akira-excel)

# Akira Excel

Akira Excel is small Java library that provides easier mapping between Excel sheet and Java classes. Akira Excel uses [Apache Poi](https://poi.apache.org/) for 2-ways mapping process.

## How it works

- Import using Maven
```
<dependency>
  <groupId>com.github.boyundefeated</groupId>
  <artifactId>akira-excel</artifactId>
  <version>1.0.1</version>
</dependency>
```
- Import using Gradle
```
compile("com.github.boyundefeated:akira-excel:1.0.1")
```

- Create a POJO to mapping with excel file
```java
public class Employee {

	@ExcelColumnTitle("Name")
	protected String name = "";

	@ExcelColumnTitle("AGE")
	protected Integer age;

	@ExcelColumnTitle("BIRTHDAY")
	protected Date birthday;
  
}
```
- Prepare excel file

|       Name         |    Age       |   Birthday  |
|--------------------|--------------|-------------|
|   Nguyen Van A     |    17        |  4/9/1994   |
|   Nguyen Van B     |    18        |  5/10/1995  |
|   Nguyen Van C     |    19        | 6/11/1996   |

- Convert Excel to POJO

```java
String path = "/home/duy/employees.xlsx";
List<Employee> employees = AkiraExcel.fromExcel(new File(path), Employee.class);
```

- Convert POJO to Excel (.xlsx)

```java
List<Employee> employees = Data.fakeEmployes();
Workbook wb = AkiraExcel.toExcel(employees);
```

## Note
- Only support .xls and .xlsx extension
- Default export to .xlsx file
- Cell style can be changed by extending DefaultCellStyle class 


## License

MIT
