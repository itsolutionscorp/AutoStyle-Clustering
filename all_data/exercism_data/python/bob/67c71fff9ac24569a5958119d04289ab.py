def hey(what):

    what = what.strip()
    words = 'Whatever.'

    if silence(what):
        words = 'Fine. Be that way!'

    elif upper(what):
        words = 'Whoa, chill out!'

    elif interrogation(what):
        words = 'Sure.'

    return words

def upper(what):
    return what.upper() == what and what.lower() != what

def interrogation(what):
    return what[-1]  == '?'

def silence(what):
    return what == ""
