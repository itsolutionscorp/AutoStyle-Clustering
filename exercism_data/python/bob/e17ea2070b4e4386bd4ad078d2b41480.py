#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip() #n
    if what == '': #1
        return 'Fine. Be that way!'
    if what.isupper(): #n
        return 'Whoa, chill out!'
    if what[-1] == '?': #1
        return 'Sure.'
    else:
        return 'Whatever.'
    return

# O(n)
#2n+1 operations
