#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if what.strip().endswith('?')== True and what.isupper()==False:
        return 'Sure.'
         
                
    if what.isspace() or len(what)==0:
        return 'Fine. Be that way!'
    if what.isupper():
        return 'Whoa, chill out!'
    else:
        return 'Whatever.'
