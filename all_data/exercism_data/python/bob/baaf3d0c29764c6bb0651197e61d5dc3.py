# -*- coding:utf-8 -*-
class ActionDispatcher(object):
    def __init__(self):
        self._actions = []
        self._default = lambda instance, what: None

    def when(self, predicate):
        def register(action):
            self._actions.append((predicate, action))
            return action

        return register

    def default(self, action):
        self._default = action

    def dispatch(self, instance, what):
        for predicate, action in self._actions:
            if predicate(instance, what):
                return action(instance, what)
        else:
            return self._default(instance, what)
        
        
class Bob(object):
    _actions = ActionDispatcher()

    @_actions.when(lambda self, what: not what)
    def _nothing(self, what):
        return 'Fine. Be that way.'
        
    @_actions.when(lambda self, what: what.isupper())
    def _yelling(self, what):
        return 'Woah, chill out!'

    @_actions.when(lambda self, what: what.endswith("?"))
    def _asking(self, what):
        return "Sure."
    
    @_actions.default
    def _whatever(self, what):
        return 'Whatever.'
    
    def hey(self, what):
        if what is None:
            what = ""
        what = what.strip()
        return self._actions.dispatch(self, what)
        
