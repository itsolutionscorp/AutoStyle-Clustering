#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    #strip all leading and trailing whitespace
    what = what.strip()
    # check if string is empty
    if not what:
        return 'Fine. Be that way!'
    # check if all letters are uppercase and if there are any letters
    if what.isupper():
        return 'Whoa, chill out!'
    # check if there is a questionmark at the end
    if what.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
