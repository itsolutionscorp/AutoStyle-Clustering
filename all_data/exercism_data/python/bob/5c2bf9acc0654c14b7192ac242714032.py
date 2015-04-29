class Bob():
    """
    Bob is a lackadaisical teenager. In conversation, his responses are very limited.
    """
    def hey(self, s):
        "Asks something to Bob, it will return somehing."

        import re

        s = s.strip()

        if len(s) == 0:
            return "Fine. Be that way!"

        if s == s.upper() and re.search("[A-Z]", s.upper()) != None:
            return "Woah, chill out!"

        if s[-1:] == "?":
            return "Sure."

        return "Whatever."
