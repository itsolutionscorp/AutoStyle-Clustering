def hey(_string):
    if not _string.strip(): return 'Fine. Be that way!'
    if _string.isupper(): return 'Whoa, chill out!'
    if _string.endswith('?'): return 'Sure.'
    return 'Whatever.'
