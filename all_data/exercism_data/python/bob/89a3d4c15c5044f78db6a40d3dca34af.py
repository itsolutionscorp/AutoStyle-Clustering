#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()

    if what == '':
        bobsays = 'Fine. Be that way!'
    elif what.isupper():
        bobsays = 'Whoa, chill out!'
    elif what[-1] == '?':
        bobsays = 'Sure.'
        if what.isupper():
            bobsays = 'Whoa, chill out!'
    else:
        bobsays = 'Whatever.'
    
    return bobsays

print hey("Let's go make out behind the gym!")
