def is_shouting(txt):
    return txt.isupper()

def is_question(txt):
    return txt.endswith('?')

def is_only_whitespace(txt):
    return not txt.strip()

def hey(txt):
    if is_only_whitespace(txt):
        return 'Fine. Be that way!'
    elif is_shouting(txt.decode('utf-8')):
        return 'Woah, chill out!'
    elif is_question(txt):
        return 'Sure.'        
    return 'Whatever.'
