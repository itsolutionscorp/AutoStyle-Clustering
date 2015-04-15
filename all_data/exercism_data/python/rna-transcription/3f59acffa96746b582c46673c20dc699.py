def is_silence(msg):
    """True if the message is empty."""
    return not msg


def is_question(msg):
    """True if the message ends with a question mark."""
    return msg[-1] == '?'


def is_shouting(msg):
    """True if the message is all uppercase."""
    return msg.isupper()


class Bob:
    """Bob is a lackadaisical teenager.

    In conversation, his responses are very limited.

    """

    mapping = [(is_silence, 'Fine, be that way.'),
               (is_question, 'Sure.'),
               (is_shouting, 'Woah, chill out!')]

    def hey(self, message):
        """Talk to Bob."""
        for test, answer in self.mapping:
            if test(message):
                return answer
        return 'Whatever.'
