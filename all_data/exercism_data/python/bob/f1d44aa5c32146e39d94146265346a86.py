#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if what.isupper(): return 'Whoa, chill out!'
    if what.strip().endswith('?'): return 'Sure.'
    if len(what.strip()) == 0: return 'Fine. Be that way!'
    return 'Whatever.'
