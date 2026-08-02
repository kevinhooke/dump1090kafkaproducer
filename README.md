# Dump1090 SBS Message Forwarder

Parses dump1090 SBS Messages output on port 30003 piped into the app, and forwards either to Kafka topic or by POSTing
to a REST api, depending on the property `runmode` in app.properties (`kafka` or `rest`).

To run, use netcat on dump1090's port 30003 and pipe into the app, for example:

`nc piaware.local 30003 | java -jar target/dump1090-kafkaproducer-0.0.1-SNAPSHOT-jar-with-dependencies.jar`

# AI Usage Disclosure

While the majority of the logic for the app has been developed by hand, Claude Code
has been used to assist with setting up the GitLab CI pipeline, and Dockerise the
app to run on a remote Docker server in my HomeLab.
