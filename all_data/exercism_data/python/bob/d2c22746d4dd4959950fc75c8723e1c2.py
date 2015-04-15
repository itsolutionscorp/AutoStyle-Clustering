class Bob(object):
    # Strings that end in ? are questions.
    def questioned(self, inString):
        if inString[-1] == '?':
            return True
        else:
            return False

    # Check that the string has content
    def saidAnything(self, inString):
        if len(inString) > 0:
            return True
        else:
            return False

    # CAPS LOCK - CRUISE CONTROL FOR COOL
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
