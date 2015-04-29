def is_shouting(string):
    return string.isupper()


def is_question(string):
    return string.endswith('?')


def is_empty_string(string):
    return string.isspace() or not string


def format_string_for_bob(string):
    return string.strip()


def get_bobs_response(sentence):
    if is_shouting(sentence):
        return 'Whoa, chill out!'
    elif is_question(sentence):
        return 'Sure.'
    elif is_empty_string(sentence):
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'


def hey(what):
    formatted_what = format_string_for_bob(what)
    response = get_bobs_response(formatted_what)
    return response
