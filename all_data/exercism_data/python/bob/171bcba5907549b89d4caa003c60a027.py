def is_shouting(string):
    return string.isupper()


def is_question(string):
    return string.endswith('?')


def is_empty_string(string):
    return not string


def format_string_for_bob(string):
    return string.strip()


def get_bobs_response(sentence):
    if is_shouting(sentence):
        response = 'Whoa, chill out!'
    elif is_question(sentence):
        response = 'Sure.'
    elif is_empty_string(sentence):
        response = 'Fine. Be that way!'
    else:
        response = 'Whatever.'
    return response


def hey(what):
    formatted_what = format_string_for_bob(what)
    response = get_bobs_response(formatted_what)
    return response
