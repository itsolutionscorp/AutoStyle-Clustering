class Bob:
    def hey(self, sentence):
        if self._is_silence(sentence):
            return 'Fine, be that way.'

        if self._is_shouting(sentence):
            return 'Woah, chill out!'

        if self._is_question(sentence):
            return 'Sure.'

        return 'Whatever.'


    def _is_silence(self, sentence):
        return sentence == ''


    def _is_shouting(self, sentence):
        return sentence.isupper()


    def _is_question(self, sentence):
        return sentence[-1] == '?'
