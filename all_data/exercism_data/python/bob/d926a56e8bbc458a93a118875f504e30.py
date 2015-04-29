__all__ = ["Bob"]

def issilence(msg):
    return msg == ""

def isshouting(msg):
    return msg.isupper()

def isquestion(msg):
    return msg.endswith("?")

class Bob(object):
    """Bob is a lackadaisical teenager."""
    def hey(self, msg):
        """In conversation, Bob's responses are very limited."""
        if issilence(msg):
            return "Fine. Be that way."
        elif isshouting(msg):
            return "Woah, chill out!"
        elif isquestion(msg):
            return "Sure."
        else:
            return "Whatever."
