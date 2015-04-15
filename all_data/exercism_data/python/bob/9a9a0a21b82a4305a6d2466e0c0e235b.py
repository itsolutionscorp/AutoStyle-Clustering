__all__ = ["Bob"]

class Bob(object):
    """Bob is a lackadaisical teenager."""
    def hey(self, msg):
        """In conversation, Bob's responses are very limited."""
        if msg == "":
            return "Fine. Be that way."
        elif msg.isupper():
            return "Woah, chill out!"
        elif msg.endswith("?"):
            return "Sure."
        else:
            return "Whatever."
