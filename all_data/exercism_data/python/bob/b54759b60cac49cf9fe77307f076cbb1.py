#Include umlaute
# -*- coding: utf-8 -*
#

class Expression:

    def __init__(self, expression):
        self.expression = expression or ""
        
    def is_silent(self):
        return len(self.expression.strip()) == 0
    
    def is_yelling(self):
        return self.expression.isupper()

    def is_asking(self):
        return self.expression.strip().endswith("?")

def hey(what):
    expression = Expression(what)
    
    if expression.is_silent():
        return 'Fine. Be that way!'
    
    if expression.is_yelling():
        return 'Whoa, chill out!'
    
    if expression.is_asking() and expression.is_yelling()==False:
        return 'Sure.'
    
    return 'Whatever.'
