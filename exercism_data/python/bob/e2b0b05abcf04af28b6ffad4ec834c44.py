def hey(what):
    reply = 'Whatever.'
    what = what.strip()

    if not what:
        reply = 'Fine. Be that way!'
    elif what.isupper():
        reply = 'Whoa, chill out!'
    elif what.endswith('?'):
        reply = 'Sure.'

    return reply
