def hey(msg):
    if is_blank(msg):
        return 'Fine. Be that way!'
    if is_yelling(msg):
        return 'Whoa, chill out!'
    if is_question(msg):
        return 'Sure.'
    return 'Whatever.'

def is_question(msg):
    # avoid reaching out of bounds in empty array with msg[-1]
    return len(msg) > 0 and msg[-1] == '?'

def is_yelling(msg):
    flag = False
    for i in msg:
        # must be an upper to be yelling
        # all numbers would never trigger this
        if i.isupper():
            flag = True
        # instant elimination
        if i.islower():
            return False
    return flag

def is_blank(msg):
    # this will never trigger for empty string
    for i in msg:
        # space characters are ignored
        if i.isspace():
            continue
        # if we hit this we encountered a non space character
        # so it must not be blank
        return False
    return True
