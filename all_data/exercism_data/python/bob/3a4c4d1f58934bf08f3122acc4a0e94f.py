#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    result = SentenceInterpreter(what)
    if result.is_silence():
        return "Fine. Be that way!"
    elif result.is_question():
        return "Sure."
    elif result.is_shouting():
        return "Whoa, chill out!"
    else:
        return "Whatever."


class SentenceInterpreter:

    def __init__(self, what):
        self.sentence = what or ""

    def is_silence(self):
        return not self.sentence.strip()

    def is_question(self):
        return self.sentence.strip().endswith("?") and not self.is_shouting()

    def is_shouting(self):
        return self.sentence.isupper()
