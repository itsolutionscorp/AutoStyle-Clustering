#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    searchstring = what.strip()

    # Setting the reponse to Whatever for anything else.
    response = 'Whatever.'

    # Check for a question.  First just check for question mark
    # in the string.
   
    # updated to use endswith per helenst
    if searchstring.endswith('?'):
        response = 'Sure.'

    # I'm yelling at you (in all caps)
    if searchstring.isupper():
        response = 'Whoa, chill out!'
    
    # I said nothing at all.
    if not searchstring:
        response = 'Fine. Be that way!'

    return response
