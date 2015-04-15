# -*- coding: utf-8 -*-


class Bob:
    def hey(self, saywat):
        if saywat == '':
            return 'Fine, be that way.'
        if saywat[-1] == '?':
            return 'Sure.'
        if saywat == saywat.upper():
            return 'Woah, chill out!'
        return 'Whatever.'
