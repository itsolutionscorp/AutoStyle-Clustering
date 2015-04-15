# Python "Bob" exercise.
# For Python 2
# Strings from bob_test come in as unicode strings

def hey(what):
    what_strip = what.rstrip()
    if not what_strip or what_strip.isspace():
        return 'Fine. Be that way!'
    elif what_strip.endswith('?') and not what_strip.isupper():
        return 'Sure.'
    elif what_strip.isupper():
        return 'Whoa, chill out!'
    else:
        return 'Whatever.'
