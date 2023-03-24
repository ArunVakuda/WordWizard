# WordWizard API
WordWizard is an API that generates meaningful words from random letters. With a single API request, you can receive a list of possible words that can be made from a given string of letters. The API also provides helpful definitions and synonyms for each word, making it a valuable resource for expanding your vocabulary and improving your writing skills.

## Getting Started
To run the WordWizard API, you'll need to have Java and Maven installed on your machine. You can download the source code from this repository, and then build and run the API using the following commands:

```bash
./mvnw package
java -jar target/words-0.0.1-SNAPSHOT.jar
```

Once the API is running, you can make requests to it using your preferred HTTP client. Here's an example of a request and response:

## Request

```bash
curl -s "http://localhost:8080/words/wordLikeLessLeter?startLetter=cat&exactLetters=false&unique=true"
```

## Response

```json
[
    {
        "wordSize": 3,
        "words": [
            {
                "id": 1798,
                "word": "Act",
                "meaning": "To perform on the stage; to represent a character."
            },
            {
                "id": 47238,
                "word": "Tac",
                "meaning": "A kind of customary payment by a tenant; -- a word used in old records."
            }
        ]
    }
]
```

