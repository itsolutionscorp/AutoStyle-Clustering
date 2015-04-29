def _is_shouting(string):
    return string.isupper()

def _is_question(string):
    return string and string[-1] == '?'

def _is_silent(string):
    return not string.strip()

RESPONSES = [
    ('Whoa, chill out!', _is_shouting),
    ('Sure.', _is_question),
    ('Fine. Be that way!', _is_silent),
    ('Whatever.', lambda str: True)
]

def hey(message):
    for response, func in RESPONSES:
        if func(message):
            return response
