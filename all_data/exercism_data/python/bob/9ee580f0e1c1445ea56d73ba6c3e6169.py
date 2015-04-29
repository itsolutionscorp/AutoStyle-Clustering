def hey(what):
    return next(iter([v for f, v in RESPONSES.iteritems() if f(what)]))

def silent(words):
    return not words.strip()

def shout(words):
    return words.isupper()

def question(words):
    return words.endswith('?') and not shout(words)

def anything(words):
    return True

RESPONSES = {silent: "Fine. Be that way!", shout: "Whoa, chill out!", question: "Sure.", anything: "Whatever."}
