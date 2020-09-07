DynamoDB Stream to Slack Lambda
=================

### Author: Vladimir Budilov
* [YouTube](https://www.youtube.com/VladimirBudilov)
* [LinkedIn](https://www.linkedin.com/in/vbudilov/)
* [Medium](https://medium.com/@budilov)


### Why? 

I didn't want to start from scratch every time. This is a templated project that will let you get started quickly

### What does it do?

Simple -- it copies your DDB Stream 'email' field to Slack. That's it. It assumes a lot and you will need to tweak it if you want to save anything but Strings, but it's easy to add that logic later on

### How? 
These 2 Parameter Store variables need to exist in order for the Lambda function to function properly. The first specifies which DDB Stream to attach to 
and the second specifies which Slack endpoint to propagate the data to.
```yaml
          arn: ${ssm:/ccPreregisterTableStreamArn}
      slackEndpoint: ${ssm:/ccPreregisterSlackEndpoint}
```

### Easy deployment? 
Yes. I'm using the Serverless framework to deploy the Lambda function to AWS and here's what you need to run:

```shell script
./gradlew clean build && ./gradlew deploy
```

If the gradlew command fails you might need to run ```gradle wrapper``` to create the appropriate gradle config dir. 

### What's next? 

Enjoy
