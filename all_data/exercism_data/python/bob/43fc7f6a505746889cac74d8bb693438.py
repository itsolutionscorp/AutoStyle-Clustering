#!/usr/bin/python

class Bob(object):
    """A lackadaisical teenager."""

    def hey(self, inp):
        """Talk.  Or at least pretend to do so."""
        if inp.isupper():
            return 'Woah, chill out!'
        elif inp.endswith('?'):
            return 'Sure.'
        elif inp.strip() == '':
            return 'Fine. Be that way!'
        else:
            return 'Whatever.'
