class Bob(object):
    
    def question(self, inString):
        return inString.endswith('?')

    
    def saidAnything(self, inString):
        return len(inString) > 0   

    
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
