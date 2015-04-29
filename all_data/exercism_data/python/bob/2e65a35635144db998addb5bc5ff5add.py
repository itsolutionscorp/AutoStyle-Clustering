#
# Skeleton file for the Python "Bob" exercise.
#
#from xml.dom.minidom import CharacterData
#import string
'''
Docstring for convention only, to test if this will
satisfy pylint
'''
whitespace = ' \n\r\t'
numbers = '0123456789-.,'
punctuation = ',.?!:;-(){}[]'

def hey(what):
    '''responds like bob to input
    '''
    response_question = 'Sure.'
    response_shouting = 'Whoa, chill out!'
    response_statement = 'Whatever.'
    response_silence = 'Fine. Be that way!'
    
    if is_silence(what):
        return response_silence

    if is_shouting(what):
        return response_shouting

    if is_question(what):
        return response_question

    return response_statement

def is_question(what):
    '''returns true if the last non-whitespace character in the input
       is a question mark.  
    '''
    if last_char(what) == '?':
        return True
    else:
        return False


def is_silence(what):
    '''returns true if a character sequence consists entirely of 
       whitespace or is empty
    '''
    for character in what:
        if character not in whitespace:
            return False

    return True


def last_char(what):
    '''gets the last significant character.
       i.e. the last character that's not a whitespace
    '''
    last = ''
    for character in what:
        if not is_silence(character):
            last = character

    return last


def is_shouting(what):
    '''Determines if string is considered as shouting by checking 
       if it matches its uppercase equivalent.
       Needs the is_only numbers() function to guard against numbers and
       punctuation only phrases, 
       which would match their uppercase equivalent completely.
    '''
    if is_only_numbers(what):
        return False
    
    if what == what.upper():
        return True
    else:
        return False

 
def is_only_numbers(what):
    '''Determines if all non-whitespace and non-punctuation characters 
       are numbers.
       helps is_shouting() to check for strings consisting entirely of numbers 
    '''
    for character in what:
        if (character not in numbers 
                and character not in punctuation 
                and character not in whitespace):
            return False
    return True
    
