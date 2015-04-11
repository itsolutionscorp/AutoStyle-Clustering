class Bob(object):
    
    def _isQuestion(self, sentence):
        return sentence.endswith('?')
    
    def _isShouting(self, sentence):
        return sentence.isupper()
    
    def _isEmptySentence(self, sentence):
        return not sentence.strip()
    
    def hey(self, sentence):
        if self._isEmptySentence(sentence):
            return 'Fine. Be that way!'
        if self._isShouting(sentence):
            return 'Woah, chill out!'
        if self._isQuestion(sentence):
            return 'Sure.'
        return 'Whatever.'
