#! /usr/bin/env python

class Bob(object):
    def __init__(self):
        pass
          
    def hey(self, phrase):

        if self.is_silent(phrase):
            return 'Fine. Be that way!'

        phrase = phrase.decode('latin1')

        if self.is_shouting(phrase):
            return 'Woah, chill out!'

        elif self.is_asking_question(phrase):
            return 'Sure.'

        else: return 'Whatever.'

    def is_shouting(self,phrase):
        return phrase.isupper()

    def is_asking_question(self,phrase):
        return phrase.endswith('?')

    def is_silent(self,phrase):
        return phrase == None or phrase.strip() == ''

            
