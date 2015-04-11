class Voice(object):

    def __init__(self,utterance):
        self.utterance = utterance.strip()

    def is_shouting(self):
        return self.utterance.isupper()

    def is_asking(self):
        return self.utterance.endswith("?")

    def is_silent(self):
        return not len(self.utterance)

def hey(utterance):

    response = "Whatever." # default response

    voice = Voice(utterance)

    if voice.is_asking() and not voice.is_shouting():
        response = "Sure."
    elif voice.is_shouting():
        response = "Whoa, chill out!"
    elif voice.is_silent():
        response = "Fine. Be that way!"

    return response
