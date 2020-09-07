package com.budilov.data

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent
import org.slf4j.LoggerFactory

class DynamoDBStreamLambda : RequestHandler<DynamodbEvent, String> {
    private val logger = LoggerFactory.getLogger("DynamoDBStreamLambda")
    private val slackEndpoint = System.getenv("slackEndpoint")
    override
    fun handleRequest(ddbEvent: DynamodbEvent, context: Context): String {

        ddbEvent.records.forEach { record ->
            logger.debug("Record $record")

            val map = mutableMapOf<String, String>()

            record.dynamodb?.newImage?.entries?.forEach {
                val key = it.key
                val value = it.value.s

                logger.debug("${record.eventName} - $key -> $value")

                map[key] = value
            }

            when (record.eventName?.toLowerCase()) {
                "insert" -> {
                    khttp.post(
                            url = slackEndpoint,
                            headers = mapOf("Content-type" to "application/json"),
                            data = """{"text":"${map["email"]}"}""" )
                }
                "modify" -> {
                    khttp.post(
                            url = slackEndpoint,
                            headers = mapOf("Content-type" to "application/json"),
                            data = """{"text":"${map["email"]}"}""" )
                }
            }
        }

        return "Successfully processed " + ddbEvent.records.size + " records."
    }
}
