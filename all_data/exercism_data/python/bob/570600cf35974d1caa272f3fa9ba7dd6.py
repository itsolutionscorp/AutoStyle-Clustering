class Bob():

    def isSilent(self, toBob):
        return (not toBob or toBob.isspace())
    def isYelling(self, toBob):
        return (toBob.isupper())
    def isAsking(self, toBob):
        return (toBob.endswith('?'))

    def hey(self, toBob):
        if self.isSilent(toBob):
            return 'Fine. Be that way!'
        elif self.isYelling(toBob):
            return 'Woah, chill out!'
        elif self.isAsking(toBob):
            return 'Sure.'
        else:
            return 'Whatever.'

