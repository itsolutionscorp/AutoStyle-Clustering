def hey(what):
    utterance = Utterance(what)
    if utterance.is_yelled():
        return 'Whoa, chill out!'
    elif utterance.is_question():
        return 'Sure.'
    elif utterance.is_silence():
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'

class Utterance:
    def __init__(self, what):
        self.what = what.strip()

    def is_question(self):
        return self.what.endswith('?')

    def is_yelled(self):
        return self.what.isupper()

    def is_silence(self):
        return not self.what
