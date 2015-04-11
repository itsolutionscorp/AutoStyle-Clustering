class Bob(object):
    def questioned(self, inString):
        if inString[-1] == '?':
            return True
        else:
            return False

    def saidAnything(self, inString):
        if len(inString) > 0:
            return True
        else:
            return False

    def shouting(self, inString):
        if inString.isupper():
            return True
        else:
            return False

    def hey(self, testableString):
        testableString = testableString.strip()

        if not self.saidAnything(testableString):
            return 'Fine. Be that way!'
        if self.shouting(testableString):
            return 'Woah, chill out!'
        if self.questioned(testableString):
            return 'Sure.'

        return 'Whatever.'
