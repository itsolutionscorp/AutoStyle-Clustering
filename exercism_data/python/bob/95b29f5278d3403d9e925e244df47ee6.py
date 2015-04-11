def yelling(u):
    """ Checks if input is a yell.

    if all letters are upper then you are yelling
    """
    is_talking = False
    for c in u:
        if not c.isalpha():
            continue
        else:
            is_talking = True
        if c.upper() != c:
            return False
    return is_talking # you cannot yell if you are not even talking.

def saying_somthing(s):
    """ Checks if the input is an empty string """
    return not s

def is_question(s):
    """ Checks if the input is a question"""
    return s and s[-1] == '?'

def hey(sentence):
    """ Hey, """
    sentence = sentence.strip()
    if saying_somthing(sentence):
        return 'Fine. Be that way!'
    if yelling(sentence):
        return 'Whoa, chill out!'
    if is_question(sentence):
        return 'Sure.'
    return 'Whatever.'
