#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    stripped_what = str.strip(what)
    if stripped_what == '': return "Fine. Be that way!"

    all_alpha = ''.join([c if c.isalpha() else '' for c in stripped_what])
    if all_alpha.isupper(): return 'Whoa, chill out!'

    if stripped_what.endswith('?'): return 'Sure.'
    return 'Whatever.'
