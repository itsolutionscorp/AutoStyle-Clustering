#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
# what is assumed to be a string. The output of hey depends on what the string is:
# No lowercase letters: 'Whoa, chill out!'
# Ends with a ?: 'Sure.'
# Whitespace only: 'Fine. Be that way!'
# Anything else: 'Whatever.'
    what = unicode(what)
# We need to be ready for weird characters.
    if what == '':
        return 'Fine. Be that way!'
# First: is the string empty?
    if what.isspace() == True:
        return 'Fine. Be that way!'
# Is the string all whitespace?
    if what.endswith(' ') == True:
# This line checks the last character of the string to see if it's whitespace.
        what = what.rstrip(' ')
# If it is, it gets stripped out. rstrip will remove it all at once.
    if what.isupper() == True:
        return 'Whoa, chill out!'
# isupper only checks cased characters, i.e. letters. If there are NO letters at all, it won't stop here.
    if what[len(what)-1] == '?':
        return 'Sure.'
# This checks the last character to see if it's a ?
    else:
        return 'Whatever.'
        
    return
