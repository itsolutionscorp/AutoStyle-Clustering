def hey(msg):
    if is_blank(msg):
        return 'Fine. Be that way!'
    if is_yelling(msg):
        return 'Whoa, chill out!'
    if is_question(msg):
        return 'Sure.'
    return 'Whatever.'

def is_question(msg):
    return msg.endswith('?')

def is_yelling(msg):
    return msg.isupper()

def is_blank(msg):
    return msg.strip() == ''
