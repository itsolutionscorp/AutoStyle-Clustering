def is_question(sentence):
    return sentence.endswith("?") 

def is_yelling(sentence):
    return sentence.isupper()

def is_silent(sentence):
    if not sentence.strip():
        return True
    return False

def hey(sentence):

    if is_yelling(sentence):
        return 'Whoa, chill out!'

    elif is_question(sentence):
        return 'Sure.'

    elif is_silent(sentence):
        return 'Fine. Be that way!'

    return 'Whatever.'
