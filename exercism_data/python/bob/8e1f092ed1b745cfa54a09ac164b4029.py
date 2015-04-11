def hey(what):
    if what.strip() == '':
        reply = 'Fine. Be that way!'
    elif what.upper() == what:
        reply = 'Whoa, chill out!'
    elif what.strip()[-1] == '?':
        reply = 'Sure.'
    else:
        reply = 'Whatever.'
    return reply
