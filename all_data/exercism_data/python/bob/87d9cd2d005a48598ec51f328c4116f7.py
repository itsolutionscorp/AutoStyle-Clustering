import re

class Spoken(object):
    def __init__(self, words):
        self.words = words

    def is_yelling(self):
        return self.words.isupper() or (self.ends_in_exclamation_mark() and  self.words.isupper())

    def is_question(self):
        return self.ends_in_question_mark()

    def is_passive_aggressive(self):
        return self.is_silent()

    #
    # Helper methods
    #

    def is_silent(self):
        return self.words.strip() == ""

    def ends_in_exclamation_mark(self):
        return not self.is_silent() and self.words[-1] == "!"

    def ends_in_question_mark(self):
        return not self.is_silent() and self.words[-1] == "?"


class Bob(object):
    def hey(self, words):
        spoken = Spoken(words)
        if spoken.is_yelling():
            return 'Woah, chill out!'
        elif spoken.is_question():
            return 'Sure.'
        elif spoken.is_passive_aggressive():
            return 'Fine. Be that way!'
        else:
            return 'Whatever.'
