class Bob(object):
    # Strings that end in ? are questions.
    def questioned(self, inString):
        return inString.endswith('?')

    # Check that the string has content
    def saidAnything(self, inString):
        return len(inString) > 0    # Maintain comparison to always have bool

    # CAPS LOCK - CRUISE CONTROL FOR COOL
    def shouting(self, inString):
        return inString.isupper()

    def hey(self, testableString):
        testableString = testableString.strip()

        if not self.saidAnything(testableString):
            return 'Fine. Be that way!'
        if self.shouting(testableString):
            return 'Woah, chill out!'
        if self.questioned(testableString):
            return 'Sure.'

        return 'Whatever.'
