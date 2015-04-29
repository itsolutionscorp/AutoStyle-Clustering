#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
    
    # use if instead of elif because return exits the function 
    # len(what) == 0 does not account for white space
    if what.strip() == '':
        return 'Fine. Be that way!'
    if what.isupper():
        return 'Whoa, chill out!'
    # what(len(what) - 1) == '?' works but python offer a methods that do just that
    if what.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
