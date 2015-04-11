class Bob:
    """A lackadaisical teenager."""
    def hey(self, phrase):
        """Say something to Bob. You might get an useful answer."""
        _statement = self.Statement(phrase)
        if _statement.is_silent():
            return "Fine. Be that way."
        if _statement.is_shouting():
            return "Woah, chill out!"
        if _statement.is_question():
            return "Sure."
        return "Whatever."

    class Statement:
        """Class analysing phrases according to Bob."""
        def __init__(self, phrase):
            self._phrase = phrase

        def is_silent(self):
            """Check if the phrase is blank."""
            return self._phrase.strip() == ""

        def is_question(self):
            """Check if the phrase end with a question mark."""
            return self._phrase.endswith("?")

        def is_shouting(self):
            """Check if the phrase is written in capital."""
            return self._phrase.isupper()
