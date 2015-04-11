class Bob:
    def hey(self, input_string):
        if self._is_silence(input_string):
            return 'Fine. Be that way.'
        if self._is_shouting(input_string):
            return 'Woah, chill out!'
        if self._is_question(input_string):
            return 'Sure.'
        return 'Whatever.'

    def _is_shouting(self, input_string):
        return input_string.isupper()

    def _is_question(self, input_string):
        return input_string.endswith('?')

    def _is_silence(self, input_string):
        return input_string is None or input_string.strip() == ''
