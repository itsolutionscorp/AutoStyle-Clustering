# BOB Module

class Bob:
    def hey(self, string):

        boby = BobInterpreter(string)

        if boby.isEmpty():
            return "Fine. Be that way!"

        elif boby.isShouting():
            return "Whoa, chill out!"

        elif boby.isQuestioning():
            return "Sure."

        else:
            return "Whatever."


class BobInterpreter():
    def __init__(self, string):
        self.string = string or ""

    def isEmpty():
        return not self.string.strip()

    def isQuestioning():
        return self.string.endswith('?')

    def isShouting():
        return self.string.isupper()
