#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
    what = what.strip()
    if what == '':
        return 'Fine. Be that way!'
    if all(all(not char.islower() for char in word) for word in what.split()):
        if any(any(char.isalpha() for char in word) for word in what.split()):
            return 'Whoa, chill out!'
    if what[-1] == '?':
        return 'Sure.'
    return 'Whatever.'
