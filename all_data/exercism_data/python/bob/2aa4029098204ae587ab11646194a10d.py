#
# "Bob" exercise.
# TR
#
def hey(what):
    if what.isupper():
        return 'Whoa, chill out!'
    if what.strip().endswith('?'):
        return 'Sure.'
    if what.strip() == '':
        return 'Fine. Be that way!'
    return 'Whatever.'
