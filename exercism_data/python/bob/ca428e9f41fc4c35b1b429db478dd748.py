class Bob:
    """Bob is a lackadaisical teenager."""
    def __init__(self):
        pass

    def hey(self, question):
        if (question == "") or (question.isspace() == True):
            return "Fine. Be that way!"
        elif question.isupper() == True:
            return "Woah, chill out!"
        elif question[-1] == "?":
            return "Sure."
        else:
            return "Whatever."
