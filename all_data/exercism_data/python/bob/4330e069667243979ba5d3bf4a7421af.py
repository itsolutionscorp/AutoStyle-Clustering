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
    return msg.isupper()

def is_blank(msg):
    return msg.strip() == ''
