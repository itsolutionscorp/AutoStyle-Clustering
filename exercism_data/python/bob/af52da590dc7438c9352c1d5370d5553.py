#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    stripped_what = str.strip(what)
    if stripped_what == '': return "Fine. Be that way!"

    if stripped_what.isupper(): return 'Whoa, chill out!'

    if stripped_what.endswith('?'): return 'Sure.'
    return 'Whatever.'
