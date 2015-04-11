def is_silent(phrase):
    return phrase is None or not phrase.strip()

def is_shouting(phrase):
    return phrase.isupper()

def is_question(phrase):
    return phrase.endswith('?')

def is_anything_else(phrase):
    return True

responses = [(is_silent,        'Fine. Be that way!'),
             (is_shouting,      'Whoa, chill out!'),
             (is_question,      'Sure.'),
             (is_anything_else, 'Whatever.')]

def hey(statement):
    for test, response in responses:
        if test(statement):
            return response

