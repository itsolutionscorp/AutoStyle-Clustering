"""
Bob exercise.
"""

class Bob(object):
    """
    A typical teenager.
    """

    @classmethod
    def hey(cls, s):
        """
        Returns a response for a typical teenager.
        """
        if s.strip() == "":
            return "Fine. Be that way!"
        elif s.isupper(): # False iff no cased letters in string
            return "Woah, chill out!"
        elif s.endswith("?"):
            return "Sure."
        else:
            return "Whatever."
