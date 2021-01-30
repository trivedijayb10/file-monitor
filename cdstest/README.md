1. provide base directory which will be used to store files in - **base.filepath** property in application.properties
2. Even if you will not provide **base.filepath** then the output directory /opt/cds_files will get created and you will see below files getting generated after you execute the jar once,
 1) writer1.txt - First file where pseudo random strings are getting stored 
 2) writer2.txt - Second file where pseudo random strings are getting stored
 3) search_results.log - File monitoring output file

**Interval Durations and Output**

1) Pseudo random strings will be generated for both the files -writer1.txt and writer2.txt at every 10 seconds.
2) For more precision in output I have stored timestamp with every line written in all 3 files
3) File monitoring will be performed at every 20seconds after initial delay of 11 seconds


Note: "^CDS$" is taken as predefined regex 
  