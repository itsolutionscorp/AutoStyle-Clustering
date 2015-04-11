'''bob.py - exercism exercise #1'''


def hey(query):
    '''function that responds to input'''
    query = query.strip()  # strip white space from statement
    if is_all_caps(query):
        response = u'Whoa, chill out!'
    elif is_question(query):
        response = 'Sure.'
    elif query:
        response = u'Whatever.'
    else:
        response = u'Fine. Be that way!'

    return response

def is_question(buf):
    '''return true if a question'''
    return buf.endswith('?')

def is_all_caps(buf):
    '''returns True if all alpha are caps'''
    result = True
    found_alpha = False
    for char in buf:
        if char.isalpha():
            found_alpha = True
            result &= char == char.upper()

    if not found_alpha:  # safety valve, if no alpha chars found
        result = False

    return result
