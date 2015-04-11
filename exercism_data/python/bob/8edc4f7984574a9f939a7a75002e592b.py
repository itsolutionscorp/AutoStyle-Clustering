# -*- coding:utf-8 -*-

class Bob(object):
    def hey(self, what):
        if what is None:
            what = ""
        what = what.strip()
        if not what:
            return 'Fine. Be that way.'
        if what.isupper():
            return 'Woah, chill out!'
        if what.endswith("?"):
            return "Sure."
        return 'Whatever.'
