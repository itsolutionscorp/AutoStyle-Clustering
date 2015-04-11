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
        elif cls._is_shouting(s):
            return "Woah, chill out!"
        elif s.endswith("?"):
            return "Sure."
        else:
            return "Whatever."

    @staticmethod
    def _is_shouting(s):
        """
        Returns true if the string is considered shouting.
        """
        seen_upper = False
        # It's possible to use a one liner, but this only iterates over the
        # string once.
        for c in s:
            if c.islower():
                return False
            elif c.isupper():
                seen_upper = True
        return seen_upper
