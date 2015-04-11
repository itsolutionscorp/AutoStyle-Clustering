#!/usr/bin/env python


class Bob:
    """Bob is a lackadaisical teenager.

    In conversation, his responses are very limited.

    Bob answers 'Sure.' if you ask him a question.
    He answers 'Woah, chill out!' if you yell at him (ALL CAPS).
    He says 'Fine. Be that way!' if you address him without actually saying
    anything.
    He answers 'Whatever.' to anything else.
    """

    def hey(self, message):
        if self.is_silence(message):
            return "Fine. Be that way!"
        elif self.is_yelled(message):
            return "Woah, chill out!"
        elif self.is_question(message):
            return "Sure."
        else:
            return "Whatever."

    def is_question(self, message):
        """Test if message is a question. Return true or false."""
        return message.endswith("?")

    def is_yelled(self, message):
        """Test if message is yelled. Return true or false."""
        return message.isupper()

    def is_silence(self, message):
        """Test if message ist empty. Return true or false."""
        return not (message and message.strip())
