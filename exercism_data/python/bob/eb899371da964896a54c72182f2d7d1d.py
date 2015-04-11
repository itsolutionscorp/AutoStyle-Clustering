__author__ = 'm097654'


def hey(t):
    length = len(t)
    strip_t = t.strip()

    if not len(strip_t):
        reply = "Fine. Be that way!"
    elif t.isupper():
        reply = 'Whoa, chill out!'
    elif t[length-1] == '?':
        reply = 'Sure.'
    elif type(t) == 'unicode':
        reply = 'Whatever.'
    else:
        reply = 'Whatever.'

    return reply
