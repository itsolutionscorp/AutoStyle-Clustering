def hey(what):
    if what.strip() == '': return 'Fine. Be that way!'
    if what.isupper(): return 'Whoa, chill out!'
    if what.endswith('?'): return 'Sure.'

    return 'Whatever.'
