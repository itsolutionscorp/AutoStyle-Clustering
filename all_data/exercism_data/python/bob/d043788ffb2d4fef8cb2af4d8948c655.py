class Bob:
    def init(self):
        pass

    def hey(self, words):
        if not isinstance(words, unicode):
            words = words.decode('utf8')

        if self.isSayingNothing(words):
            return 'Fine. Be that way!'
        if self.isYelling(words):
            return 'Woah, chill out!'
        if self.isQuestion(words):
            return 'Sure.'

        return 'Whatever.'

    def isQuestion(self, words):
        return '?' in words[len(words)-1]

    def isSayingNothing(self, words):
        return len(words.strip()) == 0

    def isYelling(self, words):
        return words.isupper()
