#
# Skeleton file for the Python "Bob" exercise.
#

def non_ascii(s):
    return ''.join(c for c in s if c not in range(0x20, 0x7f))

def all_is_upper(s):
    return bool(s == s.upper() and s != s.lower())

def is_yelling(s):
    if not s: return False
    non_asciis = non_ascii(s)
    if non_asciis and not all_is_upper(non_asciis): return False
    return (s[-1] == '!' or all_is_upper(s)) and not s.lower().startswith('let')

def hey(what):
    what = what.strip()
    if is_yelling(what): return 'Whoa, chill out!'
    if what and what[-1] == '?': return 'Sure.'
    if not what: return 'Fine. Be that way!'
    return 'Whatever.'
