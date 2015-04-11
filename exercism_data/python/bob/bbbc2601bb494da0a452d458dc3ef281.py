import re 

def hey(what):
    m = re.search(r'\?+$', what)
    if what.isupper():
        return 'Whoa, chill out!'
    elif m is not None:
        return 'Sure.'
    elif not what:
        return 'Fine. Be that way!'
    elif what.isspace():
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
