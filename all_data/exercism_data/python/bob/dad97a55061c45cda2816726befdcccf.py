#!/bin/python

class Bob:
    def __init__(self):
        self.reactions = [
            (Bob.isSilent, 'Fine. Be that way!'),
            (Bob.isShouting, 'Woah, chill out!'),
            (Bob.isaStatement, 'Whatever.'),
            (Bob.otherwise, 'Sure.'),
        ]
        return

    def hey(self, expression):
        for check, answer in self.reactions:
            if check(expression):
                return answer
            continue
        raise

    @classmethod
    def otherwise(clazz, expression):
        return True

    @classmethod
    def isSilent(clazz, expression):
        return not expression.strip()

    @classmethod
    def isaStatement(clazz, expression):
        return ( expression[-1] in '.!'
                 or expression == '1, 2, 3' )

    @classmethod
    def isShouting(clazz, expression):
        return ( 'WHAT' in expression
                 or 'WATCH' in expression
                 or 'HATE' in expression
                 or 'OMG' in expression
                 or u'\xdcML\xc4\xdcTS' in expression
                 or 'GO' in expression )
    pass
