#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    if what.rstrip() == '':
        return 'Fine. Be that way!'
        
    letters = [l for l in what if l.isalpha()]
    lowers = [l for l in what if l.islower()]
    numbers = [l for l in what if l.isdigit()]

    if len(letters) == 0 and what.rstrip()[-1] != '?':
        return 'Whatever.'

    # this second condition needs improvement
    if len(lowers) == 0 and len(what.strip()) > len(numbers) + 1:
        return 'Whoa, chill out!' 

    if what.rstrip()[-1] == '?':
        return 'Sure.'

    return 'Whatever.'
