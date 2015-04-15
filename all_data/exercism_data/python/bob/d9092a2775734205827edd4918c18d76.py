class Bob(object):
    """docstring for bob"""
    def __init__(self):
        pass

    def isQuestion(self, string):
        return string[-1:] == '?'

    def isAllCaps(self, string):
        isUppercase = False
        for letter in string:
            if letter.islower():
                return False
            if letter.isupper():
                isUppercase = True
        return True and isUppercase

    def isBlank(self, string):
        if len(string) == 0:
            return True
        for letter in string:
            if letter != ' ':
                return False
        return True

    def hey(self, string):
        if (self.isAllCaps(string)):
            return "Woah, chill out!"
        elif (self.isQuestion(string)):
            return "Sure."
        elif (self.isBlank(string)):
            return "Fine. Be that way!"
        else:
            return "Whatever."

b = Bob()
print b.isAllCaps('Aa!$')
