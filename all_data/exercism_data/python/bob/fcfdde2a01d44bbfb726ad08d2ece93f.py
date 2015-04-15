##
# author: H Ali
#
# This is the source file for the programming 
# excercise `Bob`. 
# Other people interact with Bob through
# the function `hey`, by passing what they are saying
# to Bob as the argument. 
#
# The function `hey` returns Bob's reponse
# RESPONSE              Statement Interpreted As
# `Sure`                Question
# `Whoa, chill out!'    You yelled at him
# `Fine. Be that way!'  You addressed him without saying
#                       anything
# `Whatever.`           Statement not interpreted as 
#                       eiher a question or a yell
##


import re


class Statement(object):
    '''
    Class Statement represents the statement spoken to Bob.
    It consists of methods that determine whether the statement
    has the following characteristics:
        1. it has letters
        2. it has numbers
        3. it ends with a `?` mark
        4. it ends with a `!` mark
        5. it is all capitals
    Based on these five characteristics, the stmt_type method 
    decides whether the statement is a:
        1. Question
        2. Yell
        3. Empty Statment
        4. Other, not above
    Depending on the stmt_type, the response method returns 
    Bob's appropriate response:
        yell: 'Woah, chill out!'
        question: 'Sure.'
        empty stmt: 'Fine. Be that way!'
        other: 'Whatever.
    '''
    
    def __init__(self, stmt):
        self.stmt = stmt

    def has_letters(self):
        if re.search('[a-zA-Z]', self.stmt) is None:
            return False
        else:
            return True

    def has_numbers(self):
        if re.search('[0-9]', self.stmt) is None:
            return False
        else:
            return True

    def endswith_qmark(self):
        return self.stmt.endswith('?')

    def endswith_exclamation(self):
        return self.stmt.endswith('!')

    def is_all_caps(self):
        return self.has_letters() and self.stmt == self.stmt.upper()

    def stmt_type(self):
        '''
        to determine statement type, follow this order of precedence:
            1. you yelling at me?
            2. did you ask me something?
            3. huh?
            4. Whatever, fuck you too.
        '''
        if self.is_all_caps():
            return 'yell'
        elif self.endswith_qmark():
            return 'question'
        elif not (self.has_letters() or self.has_numbers()):
            return 'empty stmt'
        else:
            return 'other'
    
    def response(self):
        resp = {'yell': 'Woah, chill out!',
                'question': 'Sure.',
                'empty stmt': 'Fine. Be that way!',
                'other': 'Whatever.'
                }
        return resp[self.stmt_type()]

def hey(stmt):
    Stmt = Statement(stmt)
    return Stmt.response()
