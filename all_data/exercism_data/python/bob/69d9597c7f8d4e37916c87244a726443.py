#
# Skeleton file for the Python "Bob" exercise.
#

#He answers 'Whoa, chill out!' if you yell at him.

#Bob answers 'Sure.' if you ask him a question.

#He says 'Fine. Be that way!' if you address him without actually saying
#anything.

#He answers 'Whatever.' to anything else.



def hey(what):
    #Check if the sentence is just white space. (Not actually saying anything)
    if (''.join(what.split()) == ''):
        return "Fine. Be that way!"
    
    #Check if the sentence is in all caps, and contains characters.
    if(what.upper() == what and what.lower() != what):
       return "Whoa, chill out!"

    #Check if the sentence is a question
    if(''.join(what.split())[-1] == '?'):
        return "Sure."

    #Base case
    return "Whatever."
