class Bob():
    def hey(self, text):
        text = text.strip()
        
        if self._is_silence(text):
            answer = 'Fine. Be that way.'
        elif self._is_shout(text):
            answer = 'Woah, chill out!'
        elif self._is_question(text):
            answer = 'Sure.'
        else:
            answer = 'Whatever.'

        return answer

    def _is_silence(self, text):
        return text == ''

    def _is_shout(self, text):
        return text.isupper()

    def _is_question(self, text):
        return text.endswith('?')
