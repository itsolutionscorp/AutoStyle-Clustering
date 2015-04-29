# Based on Bahktin's notion of dialogic exchange
class Bob:
    def hey(self, statement):
        return Exchange(statement).response()

class Exchange:
    def __init__(self, statement):
        self.utterance = Utterance(statement)

    def response(self):
        if self.utterance.isloud():
            return 'Woah, chill out!'
        elif self.utterance.isaquestion():
            return 'Sure.'
        elif self.utterance.issilent():
            return 'Fine. Be that way!'
        else:
            return 'Whatever.'

class Utterance:
    def __init__(self, statement):
        self._setstatement(statement)

    def isloud(self):
        return self.statement.isupper()

    def isaquestion(self):
        return self.statement.endswith('?')

    def issilent(self):
        return not self.statement

    def _setstatement(self, s):
        if s is None:
            self.statement = ''
        else:
            self.statement = s.strip()
