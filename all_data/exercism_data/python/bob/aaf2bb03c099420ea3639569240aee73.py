#
# Skeleton file for the Python "Bob" exercise.
#
#
# Bob is a lackadaisical teenager. In conversation, his responses are very limited.
#
# Bob answers 'Sure.' if you ask him a question.
#
# He answers 'Whoa, chill out!' if you yell at him.
#
# He says 'Fine. Be that way!' if you address him without actually saying
# anything.
#
# He answers 'Whatever.' to anything else.
#

def hey(what):
    
    # Removing whitespace at end of input
    what = what.rstrip()

    # Check for shouting (defined in the test as allcaps)
    if what.isupper() :        
        return "Whoa, chill out!"
    
    # Check for question mark
    if what.endswith("?") : 
        return "Sure."
    
    # Check for silence
    if what == "" :
        return "Fine. Be that way!"
    
    # Otherwise, default response
    return "Whatever."
