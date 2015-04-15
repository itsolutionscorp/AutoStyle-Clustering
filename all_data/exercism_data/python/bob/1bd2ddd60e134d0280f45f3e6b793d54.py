#
# Skeleton file for the Python "Bob" exercise.
#
#Bob answers 'Sure.' if you ask him a question.
#He answers 'Whoa, chill out!' if you yell at him.
#He says 'Fine. Be that way!' if you address him without actually saying anything.
#He answers 'Whatever.' to anything else.
def hey(what):
    # Dictionary of responces
    toreturn = { '?' : 'Sure.', '!' : 'Whoa, chill out!','':  'Fine. Be that way!' ,'w':'Whatever.' }

    # strip any whitespace
    what =what.replace(" ", "")
    what =what.strip()

    #test cases
    if what == '' : case = ''       # empty after stripping white
    elif what.isupper(): case = '!' # all upper = yelling
    elif what[-1]=='?' : case = '?' # ? = ? 
    else: case = 'w'                 # all else is whatever
    return toreturn[case]
