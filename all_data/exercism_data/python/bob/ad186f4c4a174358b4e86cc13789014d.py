def hey(text):
    text = text.strip()
    if not text:
        return u'Fine. Be that way!'
    elif text.isupper():
        return u"Whoa, chill out!"
    elif text[-1] == "?":
        return u"Sure."
    else:
        return u'Whatever.'
