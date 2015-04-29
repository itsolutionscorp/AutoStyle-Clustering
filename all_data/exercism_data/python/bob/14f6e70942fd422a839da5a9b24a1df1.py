#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    arg = what
    suffix = "?"
    if arg.isupper(): return ('Whoa, chill out!')
    elif arg.endswith(suffix): return ('Sure.')
    elif arg and not arg.isspace(): return ('Whatever.')
    else: return ('Fine. Be that way!')
