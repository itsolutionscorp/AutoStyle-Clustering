""" Bob is a lackadaisical teenager."""
from re import search

class Bob(object):
    """
    In conversation, his responses are very limited.
    He answers 'Sure.' if you ask him a question.
    He answers 'Woah, chill out!' if you yell at him.
    He says 'Fine. Be that way!' if you address him without saying anything.
    He answers 'Whatever.' to anything else.
    """

    def hey(self, string):
        """Address Bob with the phrase contained by string."""

        if self.nothing(string):
            return "Fine. Be that way!"

        if self.yelling(string):
            return "Woah, chill out!"

        if self.question(string):
            return "Sure."

        if self.anything(string):
            return "Whatever."

    def __init__(self):
        pass

    def nothing(self, string):
        """Check if string is empty or whitespace."""
        pattern = r'^\s*$'
        return search(pattern, string)

    def yelling(self, string):
        """Check if string upper case."""
        if string.isupper():
            return True

    def question(self, string):
        """Check if string ends with a question mark."""
        pattern = r'^.*\?$'
        return search(pattern, string)

    def anything(self, string):
        """Match any string."""
        pattern = r'^.*$'
        return search(pattern, string)
