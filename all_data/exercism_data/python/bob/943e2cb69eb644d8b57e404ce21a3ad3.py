class Bob:

    def hey(self, sentence):
        if sentence == None or sentence == '' or sentence.isspace():
            return 'Fine. Be that way!'
        elif sentence == sentence.upper():
            return 'Woah, chill out!'
        elif sentence[-1] == '?':
            return 'Sure.'
        else:
            return 'Whatever.'
