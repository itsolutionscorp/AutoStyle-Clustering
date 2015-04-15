class Bob:
    def hey(self, raw_utterance):
        utterance = Utterance(raw_utterance)

        if utterance.is_silent():   return "Fine, be that way."
        if utterance.is_shout():    return "Woah, chill out!"
        if utterance.is_question(): return "Sure."
        else:                       return "Whatever."

class Utterance:
    def __init__(self, value):
        self._value = value

    def is_silent(self):
        return not self._value

    def is_shout(self):
        return self._value.isupper()

    def is_question(self):
        return self._value.endswith("?")
