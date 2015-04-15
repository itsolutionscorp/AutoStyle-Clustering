def hey(statement):
    if not statement.strip(): return 'Fine. Be that way!'
    if statement.isupper(): return 'Whoa, chill out!'
    if statement.endswith('?'): return 'Sure.'
    return 'Whatever.'
