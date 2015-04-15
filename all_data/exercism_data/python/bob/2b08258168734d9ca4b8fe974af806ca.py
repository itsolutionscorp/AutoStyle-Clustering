def hey(what):
    response = ''
    if is_question(what) and not is_shouting(what):
        response = 'Sure.'
    elif is_silence(what):
        response = 'Fine. Be that way!'
    elif is_shouting(what):
        response = 'Whoa, chill out!'
    else:
        response = 'Whatever.'
    return response

def is_question(what):
    if what[-1:] == '?':
        return True

def is_silence(what):
    if (what == '') or (what.isspace()):
        return True

def is_shouting(what):
    if what.isupper():
        return True
