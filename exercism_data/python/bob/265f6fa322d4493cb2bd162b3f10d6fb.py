#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.rstrip()
    integers = [int(s) for s in list(what) if s.isdigit()]
    if what:
        if  what[-1] == '?' and not what.isupper():
            ans = 'Sure.'
        elif what.isupper():
            ans = 'Whoa, chill out!'
        else:
            ans = 'Whatever.'
    else:
        ans = 'Fine. Be that way!'
        
    return ans
