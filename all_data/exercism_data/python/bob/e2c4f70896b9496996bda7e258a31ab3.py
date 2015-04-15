class Bob:
    def hey(self, phrase):
        if( self.isShouting( phrase)):
            return "Woah, chill out!"
        if( self.isQuestion(phrase)):
            return 'Sure.'
        if( self.isSilence(phrase)):
            return 'Fine. Be that way.'

        return 'Whatever.'

    def isShouting( self, phrase):
        return phrase.isupper()

    def isSilence(self, phrase):
        return  len(phrase.strip()) ==0

    def isQuestion( self, phrase):
        return len(phrase) > 0 and phrase[-1] == '?'
