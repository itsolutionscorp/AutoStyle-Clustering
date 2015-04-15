"""
 Implementation file for the Python "Bob" exercise module.
"""

def is_question(what):
    """ Questions end in '?' and are not empty"""
    if len(what) > 0 and what[-1] == '?':
        return True
    else:
        return False

def is_shouting(what):
    """ Shouting is all upper case"""
    if what.isupper():
        return True
    else:
        return False

def is_silence(what):
    """ Silence is either empty or whitespace"""
    if len(what) == 0:
        return True
    elif what.isspace():
        return True
    else:
        return False

def hey(what):
    """ Tests for each type of statement and responds as Bob would

    He answers as follows:
    'Sure.' if you ask him a question.

    'Whoa, chill out!' if you yell at him.

    'Fine. Be that way!' if you don't actually say anything.

    'Whatever.' to anything else.
    """
    if is_shouting(what):
        return 'Whoa, chill out!'
    elif is_question(what):
        return 'Sure.'
    elif is_silence(what):
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
