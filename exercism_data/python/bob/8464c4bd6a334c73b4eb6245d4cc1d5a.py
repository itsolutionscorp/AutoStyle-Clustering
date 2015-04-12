#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    searchstring = what

    # Setting the reponse to Whatever for anything else.
    response = 'Whatever.'

    # Check for a question.  First just check for question mark
    # in the string.
   
    # updated to use endswith per helenst
    if searchstring.endswith('?'):
        response = 'Sure.'
    # Check for a question with trailing whitespace.
    if ('?' in searchstring) and (searchstring[-1:]==' '):
        response = 'Sure.'

    # I'm yelling at you (in all caps)
    if searchstring.isupper():
        response = 'Whoa, chill out!'

    # I'm not really saying anything at all.
    if searchstring.isspace():
        response = 'Fine. Be that way!'
    
    # Know I've gone all euro on you (umlate check)
    if unichr(252) in searchstring:
        response = 'Whatever.'
    
    # I said nothing at all.
    if not searchstring:
        response = 'Fine. Be that way!'

    return response