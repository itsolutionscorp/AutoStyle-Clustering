__all__ = ["Bob"]

class Bob(object):
    """Bob is a lackadaisical teenager."""
    def hey(self, msg):
        """In conversation, Bob's responses are very limited."""
        if msg == "":
            # Being silent
            return "Fine. Be that way."
        elif msg.isupper():
            # Shouting
            return "Woah, chill out!"
        elif msg.endswith("?"):
            # Asking a question
            return "Sure."
        else:
            return "Whatever."
