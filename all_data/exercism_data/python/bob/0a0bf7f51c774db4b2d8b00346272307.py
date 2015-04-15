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

PAIRS = [
    (is_yell, REPLY_YELL),
    (is_question, REPLY_QUESTION),
    (is_nothing, REPLY_NOTHING),
]

def hey(query):
    for criteria, response in PAIRS:
        if criteria(query):
            return response

    return REPLY_ELSE
