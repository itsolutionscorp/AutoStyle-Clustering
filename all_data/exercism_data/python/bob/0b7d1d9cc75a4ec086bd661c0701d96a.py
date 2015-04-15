class Bob:
    def hey(self, sentence):
        if sentence.strip() == '':
            return 'Fine. Be that way!'
        elif sentence.isupper():
            return 'Woah, chill out!'
        elif sentence[-1] == "?":
            return 'Sure.'
        else:
            return 'Whatever.'
