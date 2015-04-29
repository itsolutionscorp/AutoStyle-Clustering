# -*- coding: utf-8 -*-
# Bob the lackadaisical teenager
import string

def is_question(statement):
    '''Returns true if the statement is a question.
    '''

    return statement[-1] == '?'


def replace_umlauts(the_str):
    umlauts   = {u"Ü":"U", u"Ä": "A", u"ä":"a"}
    
    replaced_str = ''
    
    for l in the_str:
        if l in umlauts.keys():
            replaced_str = replaced_str + umlauts[l]
        else:
            replaced_str = replaced_str + l

    return replaced_str
                
                
def is_yelling(original_statement):
    '''Returns True is the statement is interpreted as yelling.
    '''

    '''    if statement[-1] == '!' and len([x for x in statement if x not in string.letters]):
        return True
        '''

    statement = replace_umlauts(original_statement)

    all_letters = [x for x in statement if x in string.letters]

    if len(all_letters) > 0:
        percent_caps = len([x for x in statement if x in string.uppercase]) / float(len(all_letters)) 
        return percent_caps > 0.99
    else:
        return False


def is_nonverbal(statement):
    '''Returns true if there are no letters/words in the statement.
    '''

    # string is all whitespace OR
    # contains non-ascii chars

    if len(statement.strip()) == 0:
        return True

    # if statement.strip()[0] not in string.ascii_letters:
    #     return True
    
    #if len([x for x in statement if x not in string.printable]):
    #    return True

def is_alphabetic(statement):
    
    return len([x for x in statement if x not in string.letters + string.punctuation + string.whitespace]) == 0


def hey(statement):
    '''Function to talk to Bob, the lackadaisical teenager.'''

    response = {
        'question': 'Sure.',
        'yelling': 'Whoa, chill out!',
        'non-verbal': 'Fine. Be that way!'
        }

    if len(statement.strip()) == 0:
        return response['non-verbal']

    if is_question(statement) and not is_yelling(statement):
        # Bob answers 'Sure.' if you ask him a question.
        return response['question']

    elif is_yelling(statement):
        # He answers 'Whoa, chill out!' if you yell at him.
        return response['yelling']

    elif is_nonverbal(statement):
        # He says 'Fine. Be that way!' if you address him without actually
        # saying anything.
        return response['non-verbal']
    
    # He answers 'Whatever.' to anything else.
    return 'Whatever.'


if __name__ == '__main__':

    import sys

    message = sys.argv[1]

    print message
    print hey(message)
