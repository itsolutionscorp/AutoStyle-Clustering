class Bob:
    def hey(self, words):
        if not isinstance(words, unicode):
            words = words.decode('utf8')

        if self.is_saying_nothing(words):
            return 'Fine. Be that way!'
        if self.is_yelling(words):
            return 'Woah, chill out!'
        if self.is_question(words):
            return 'Sure.'

        return 'Whatever.'

    def is_question(self, words):
        return words[-1] == '?'

    def is_saying_nothing(self, words):
        return words.strip() == ''

    def is_yelling(self, words):
        return words.isupper()
