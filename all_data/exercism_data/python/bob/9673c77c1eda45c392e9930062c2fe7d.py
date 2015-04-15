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

    def endswith_qmark(self):
        if self.stmt.endswith('?'):
            return True
        else:
            return False

    def is_empty(self):
        if self.stmt.strip() == '':
            return True
        else:
            return False
    
    def is_yell(self):
        if self.stmt.isupper():
            return True
        else:
            return False

    def stmt_type(self):
        '''
        to determine statement type, follow this order of precedence:
            1. you yelling at me?
            2. did you ask me something?
            3. huh?
            4. Whatever, fuck you too.
        '''
        if self.is_yell():
            return 'yell'
        elif self.endswith_qmark():
            return 'question'
        elif self.is_empty():
            return 'empty stmt'
        else:
            return 'other'
    
class StatementBob(Statement):
    '''
    StatementBob subclasses from Statement. It relies on the parent's
    statement type determination, and adds a response method for 
    returning the expected response from Bob
    
    Bob's appropriate response:
        yell: 'Woah, chill out!'
        question: 'Sure.'
        empty stmt: 'Fine. Be that way!'
        other: 'Whatever.
    '''
    def response(self):
        resp = {'yell': 'Woah, chill out!',
                'question': 'Sure.',
                'empty stmt': 'Fine. Be that way!',
                'other': 'Whatever.'
                }
        return resp[self.stmt_type()]

def hey(stmt):
    Stmt = StatementBob(stmt)
    return Stmt.response()
