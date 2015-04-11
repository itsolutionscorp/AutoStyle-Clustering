import string, unicodedata

def hey(what):
    request = what.strip()

    response = ''

    if not request:
        response = 'Fine. Be that way!'

    elif request.upper() == request and request.lower() != request:
        response = 'Whoa, chill out!'

    elif request[-1:] == '?':
        response = 'Sure.'

    else:
        response = 'Whatever.'

    return response

