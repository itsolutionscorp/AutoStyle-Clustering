def is_yell(msg):
    """msg is a yell if all characters are uppercase"""
    return msg.isupper()

def is_question(msg):
    """msg contains a question if there is a question
    mark at the end of the string"""
    return msg.endswith('?')

def is_nothing(msg):
    """msg is nothing if it's nothing or one or morespaces"""
    return msg == '' or msg.isspace()

def hey(msg):
    if is_nothing(msg):
        return 'Fine. Be that way!'
    if is_yell(msg):
        return 'Whoa, chill out!'
    if is_question(msg):
        return 'Sure.'
    return 'Whatever.'
