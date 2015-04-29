#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if what.endswith('?')== True and what.isupper()==False:
        return 'Sure.'
    if what.endswith(' '):
         for i in reversed(what):
             if i!=' ':
                 if i=='?':
                     return 'Sure.'
		     break
		 else:
                     return 'Whatever.'
             else:
                 pass
            
                
    if what.isspace() or len(what)==0:
        return 'Fine. Be that way!'
    if what.isupper():
        return 'Whoa, chill out!'
    else:
        return 'Whatever.'
