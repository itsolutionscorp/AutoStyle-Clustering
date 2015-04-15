def is_silent(utterance):
    return utterance == ''

def is_shouty(utterance):
    return utterance.isupper()

def is_question(utterance):
    return utterance.endswith('?')

class Bob:
    def hey(self, utterance):
        if is_silent(utterance):
            return 'Fine. Be that way.'
        elif is_shouty(utterance):
            return 'Woah, chill out!'
        elif is_question(utterance):
            return 'Sure.'
        else:
            return 'Whatever.'
