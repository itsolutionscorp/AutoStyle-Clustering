def is_nothing(sentence):
    return sentence == ''

def is_yelling(sentence):
    return sentence.isupper()

def is_question(sentence):
    return sentence.endswith('?')

def hey(sentence):
    """sentence said to bob"""
    sentence = sentence.strip()

    if is_nothing(sentence):
        return 'Fine. Be that way!'
    elif is_yelling(sentence):
        return 'Woah, chill out!'
    elif is_question(sentence):
        return 'Sure.'
    else:
        return 'Whatever.'
