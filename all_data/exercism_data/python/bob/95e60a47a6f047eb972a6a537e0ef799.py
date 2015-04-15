import re

unicode_letter_patern = re.compile('[^\W\d_]')


def is_question(text):
    return text.endswith('?')


def is_shouting(text):
    return (
        unicode_letter_patern.search(text) and
        text == text.upper()
    )


def is_silence(text):
    return re.search('^[ ]*$', text)


class Bob(object):
    def __init__(self):
        self.brain = [
            (is_silence, 'Fine. Be that way!'),
            (is_shouting, 'Woah, chill out!'),
            (is_question, 'Sure.'),
        ]
        self.default_answer = 'Whatever.'

    def hey(self, text):
        for predicat, response in self.brain:
            if predicat(text):
                return response
        return(self.default_answer)
