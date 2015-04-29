# -*- coding: utf-8 -*-


class Bob:
    def hey(self, saywat):
        if saywat is None or saywat.strip() == '':
            return 'Fine. Be that way!'
        if saywat.isupper():
            return 'Woah, chill out!'
        if saywat[-1] == '?':
            return 'Sure.'
        return 'Whatever.'
