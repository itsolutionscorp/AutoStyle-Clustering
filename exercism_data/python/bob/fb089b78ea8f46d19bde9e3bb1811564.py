# -*- coding:utf-8 -*-

class Bob(object):
    def _nothing(self):
        return 'Fine. Be that way.'
        
    def _yelling(self):
        return 'Woah, chill out!'

    def _asking(self):
        return "Sure."
    
    def _whatever(self):
        return 'Whatever.'
    
    _actions = (
        # predicate / action
        (lambda what: not what, _nothing),
        (lambda what: what.isupper(), _yelling),
        (lambda what: what.endswith("?"), _asking),
        )

    def hey(self, what):
        if what is None:
            what = ""
        what = what.strip()
        for predicate, action in self._actions:
            if predicate(what):
                return action(self)
        else:
            return self._whatever()
        
