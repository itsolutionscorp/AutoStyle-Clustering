#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()

    if what.isupper():
    	return 'Whoa, chill out!'

    if what.endswith('?') and not what.isupper():
        return 'Sure.'

    if what == '':
        return 'Fine. Be that way!'
  
    return 'Whatever.'