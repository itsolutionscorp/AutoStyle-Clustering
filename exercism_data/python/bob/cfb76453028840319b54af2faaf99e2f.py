#!/usr/bin/env python


def all_caps(s):
    """Test whether all the letters in the string are capitalized."""
    alphas = [c for c in s if c.isalpha()]
    if alphas == []:
        return False
    for x in alphas:
        if not x.isupper():
            return False
    return True


class Bob(object):

    def hey(self, q=''):
        if all_caps(q):
            return 'Woah, chill out!'
        elif q.endswith('?'):
            return 'Sure.'
        elif q.strip() == '':
            return 'Fine. Be that way!'
        else:
            return 'Whatever.'
