def hey(what):
    reply = 'Fine. Be that way!'
    what_ = what.strip()

    if len(what_):
        if what_.isupper():
            reply = 'Whoa, chill out!'
        elif what_.endswith('?'):
            reply = "Sure."
        else:
            reply = 'Whatever.'

    return reply
