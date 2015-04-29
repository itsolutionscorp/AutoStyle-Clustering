#!/usr/bin/env python3


class Message:

    def __init__(self, message):
        self.message = message

    @property
    def has_alpha(self):
        return any(map(str.isalpha, self.message))

    @property
    def is_empty(self):
        return self.message is "" or self.message.isspace()

    @property
    def is_question(self):
        return self.message.endswith("?")

    @property
    def is_yelling(self):
        return (
            self.has_alpha and
            all(map(lambda l: not l.isalpha() or l.isupper(), self.message))
        )


class Bob:

    """
    Bob
    ===

    Bob is a lackadaisical teenager. In conversation, his responses are very
    limited.

    Bob answers 'Sure.' if you ask him a question.

    He answers 'Woah, chill out!' if you yell at him.

    He says 'Fine. Be that way!' if you address him without actually saying
    anything.

    He answers 'Whatever.' to anything else.

    """

    def hey(self, message):

        message = Message(message)

        if message.is_empty:
            return "Fine. Be that way!"

        if message.is_yelling:
            return 'Woah, chill out!'

        if message.is_question:
            return "Sure."

        return 'Whatever.'
