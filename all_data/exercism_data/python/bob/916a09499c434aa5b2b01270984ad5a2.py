import string

def hey(what):

    input = what.strip()
    if input == '': return 'Fine. Be that way!'
    elif input.isupper(): return 'Whoa, chill out!'
    elif input.endswith('?'): return 'Sure.'
    else: return 'Whatever.'
