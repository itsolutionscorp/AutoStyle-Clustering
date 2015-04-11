def hey(s):
    if s.isupper(): return 'Whoa, chill out!'
    if s.endswith('?'): return 'Sure.'
    if not s.strip(): return 'Fine. Be that way!'
    return 'Whatever.'
