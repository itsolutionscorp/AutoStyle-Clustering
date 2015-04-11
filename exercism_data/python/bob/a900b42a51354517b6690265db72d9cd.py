class Bob():
    def hey(self, word):
        sentence = Discussion(word)
        if sentence.empty():
            return 'Fine. Be that way!'
        elif sentence.yell():
            return 'Woah, chill out!'
        elif sentence.question():
            return 'Sure.'
        return 'Whatever.'


class Discussion():
    def __init__(self, message):
        self.message = message

    def empty(self):
        return self.message is None or len(self.message.strip()) == 0

    def yell(self):
        return self.message == self.message.upper()

    def question(self):
        return self.message.endswith('?')
