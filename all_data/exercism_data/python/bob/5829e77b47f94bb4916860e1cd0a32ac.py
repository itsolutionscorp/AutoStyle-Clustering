class Bob(object):

    def hey(self, sentence):
        interaction = Interaction(sentence)

        if interaction.is_silence():
            return 'Fine. Be that way!'
        elif interaction.is_shouting():
            return 'Woah, chill out!'
        elif interaction.is_question():
            return 'Sure.'
        else:
            return 'Whatever.'


class Interaction(object):

    def __init__(self, sentence):
        self.sentence = sentence

    def is_silence(self):
        return self.sentence is None or not self.sentence.strip()

    def is_question(self):
        return self.sentence.endswith('?')

    def is_shouting(self):
        return self.sentence.isupper()
