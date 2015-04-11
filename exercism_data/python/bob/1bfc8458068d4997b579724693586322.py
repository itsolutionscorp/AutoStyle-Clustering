REPLY_YELL      = 'Whoa, chill out!'
REPLY_QUESTION  = 'Sure.'
REPLY_NOTHING   = 'Fine. Be that way!'
REPLY_ELSE      = 'Whatever.'

def is_yell(query):
    return query.isupper()

def is_question(query):
    return query.endswith('?')

def is_nothing(query):
    return query.strip() == ''

def hey(query):
    if is_yell(query):
        return REPLY_YELL

    if is_question(query):
        return REPLY_QUESTION

    if is_nothing(query):
        return REPLY_NOTHING

    return REPLY_ELSE
