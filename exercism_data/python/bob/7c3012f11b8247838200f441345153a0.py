def hey(what):
    what = what.strip()  # Get rid of whitespace
    if len(what) == 0:
        reply = 'Fine. Be that way!'
    elif what.isupper():
        reply = 'Whoa, chill out!'
    elif what.endswith('?'):
        reply = 'Sure.'
    else:
        reply = 'Whatever.'
    return reply
