#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if what.isupper():
        return "Whoa, chill out!"
    if(len(what)> 0 and what[len(what.rstrip())-1] == "?"):
        return 'Sure.'
    if ( len(what) == 0 or '\t' in  what):
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
