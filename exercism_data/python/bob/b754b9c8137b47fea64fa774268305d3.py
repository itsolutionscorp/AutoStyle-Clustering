def hey(what):
    what = what.strip()

    if not what:  # nothing
        response = 'Fine. Be that way!'
    elif what.isupper():  # yelling
        response = 'Whoa, chill out!'
    elif what.endswith('?'):  # question
        response = 'Sure.'
    else:  # everything else
        response = 'Whatever.'

    return response
