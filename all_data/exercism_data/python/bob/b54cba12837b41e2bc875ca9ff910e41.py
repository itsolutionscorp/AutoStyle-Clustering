#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    return ('Fine. Be that way!' if len(what.strip()) == 0 else 'Whoa, chill out!' if what.isupper() else 'Sure.' if '?' in what.strip()[-1] else 'Whatever.')
