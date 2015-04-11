def hey(expression):
    """test whether the expression is a question, a silence
        or you are yelling"""

    if expression.isupper():
    #are you yelling? test if expresion is in uppercase

        return 'Whoa, chill out!'

    elif expression.endswith('?'):
    #Are you asking a question? test if expresion is ending with '?'

        return 'Sure.'

    elif expression.isspace() or len(expression) == 0:
    #Are you in silence? Test if expresion is full of spaces or empty.

        return 'Fine. Be that way!'

    else:
        return 'Whatever.'
        #For the Rest of expressions ... Whatever.
