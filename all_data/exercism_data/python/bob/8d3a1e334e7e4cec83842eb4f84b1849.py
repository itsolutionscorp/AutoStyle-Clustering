class Statement:
    def __init__(self, input_string):
        self._input_string = input_string

    def is_shouted(self):
        return self._input_string.isupper()

    def is_question(self):
        return self._input_string.endswith('?')

    def is_silence(self):
        return self._input_string is None or self._input_string.strip() == ''

class Bob:
    def hey(self, input_string):
        statement = Statement(input_string)
        if statement.is_silence():
            return 'Fine. Be that way.'
        if statement.is_shouted():
            return 'Woah, chill out!'
        if statement.is_question():
            return 'Sure.'
        return 'Whatever.'
