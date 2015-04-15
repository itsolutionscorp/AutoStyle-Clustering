#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()
    return \
        'Whoa, chill out!' if what.isupper() else \
        'Sure.' if what.endswith('?') else \
        'Fine. Be that way!' if not what else \
        'Whatever.'
