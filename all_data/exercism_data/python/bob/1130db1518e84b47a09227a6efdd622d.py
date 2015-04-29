#
# Skeleton file for the Python "Bob" exercise.
#
## Bob
#Bob answers 'Sure.' if you ask him a question.#
#He  answers 'Whoa, chill out!' if you yell at him.
#He  says    'Fine. Be that way!' if you address him without actually saying
#He  answers 'Whatever.' to anything else.

def hey(what):
    
    #cleans white space    
    what = what.strip()   
    
    #blank test
    if len(what) == 0:
        response = 'Fine. Be that way!'
    #tests for no alpha characters    
    elif (what == what.upper()) and (what == what.lower()):
        #needs another question test
        if what[len(what)-1] == '?':
            response = 'Sure.'
        else:
            response = 'Whatever.'
    #test for shouting
    elif what == what.upper():
        response = 'Whoa, chill out!'
    #test for question
    elif what[len(what)-1] == '?':
        response = 'Sure.'
    #anything else
    else:
        response = 'Whatever.'
    
 #   print response
    return response
    

    
