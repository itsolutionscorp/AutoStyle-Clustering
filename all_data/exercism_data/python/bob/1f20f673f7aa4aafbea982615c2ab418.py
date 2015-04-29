class Bob(object):

    def isQuestion(self, string):
        return string.endswith('?')

    def isAllCaps(self, string):
        return string.isupper()

    def isBlank(self, string):
        return string.strip() == ''

    def hey(self, string):
        if self.isAllCaps(string):
            return "Woah, chill out!"
        elif self.isQuestion(string):
            return "Sure."
        elif self.isBlank(string):
            return "Fine. Be that way!"
        else:
            return "Whatever."

b = Bob()
