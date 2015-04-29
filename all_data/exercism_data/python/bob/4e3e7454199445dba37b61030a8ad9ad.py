import regex # for regular expressions

#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    
    # default answer
    answer = 'Whatever.'
    
    # check for questions: ? at the end optionally followed by whitespace
    if regex.search( r'\?\s*$', what ):
        answer = 'Sure.'
        
    # check for shouting: remove non-letters, then check for no lowercase
    cleaned = regex.sub( r'[^\w]|\d', '', what )
    if len( cleaned ) > 0 and not regex.search( r'\p{Ll}', cleaned ):
        answer = 'Whoa, chill out!'
        
    # check for empty addresses: remove whitespace, then check length
    if 0 == len( regex.sub( r'\s', '', what ) ):
        answer = 'Fine. Be that way!'
    
    return answer
