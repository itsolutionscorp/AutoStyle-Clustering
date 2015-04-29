class Bob(object):
    
    def hey(self, sentence):
        if self._is_silence(sentence):
            return 'Fine. Be that way!'
        elif self._is_yelling(sentence):
            return 'Woah, chill out!'
        elif self._is_question(sentence):
            return 'Sure.'
        else:
            return 'Whatever.'
        
    def _is_silence(self, sentence):
        return not(sentence and sentence.strip())
    
    def _is_yelling(self, sentence):
        return sentence.isupper()
    
    def _is_question(self, sentence):
        return sentence.endswith('?')
    
    
