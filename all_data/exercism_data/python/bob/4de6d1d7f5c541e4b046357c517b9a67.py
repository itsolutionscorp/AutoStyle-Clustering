#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    if chill(what) == True:
        return 'Whoa, chill out!'
    elif fine(what) == True:
        return 'Fine. Be that way!'
    elif  sure(what) == True:
        return 'Sure.'
    else:
        return 'Whatever.'
    return

## Any string that has a question mark at the end will return true. Bob will say 'Sure'
def sure(what):
    return what.endswith('?')
        
##Any string that is blank, or not saying anything bob should say 'Fine. Be that way!
def fine(what):
    what = what.strip()
    if what == '':
        return True
## Any string that is in all uppercase letters (yelling) will return true. Bob will say 'Whoa, chill out!'
def chill(what):
   return what.isupper()
