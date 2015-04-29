#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    #check if question
    if what.strip()[-1:] == '?':
        return 'Sure.'
    #check if yelling with upper
    elif what.strip().isupper():
        return 'Whoa, chill out!'
    #check if nothing said
    elif what == '':
        return 'Fine. Be that way!'
    #all other sentences
    else:
         return 'Whatever.'

