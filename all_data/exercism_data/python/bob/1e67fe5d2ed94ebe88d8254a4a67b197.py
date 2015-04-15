#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    #Bob answers 'Sure.' if you ask him a question.
    #He answers 'Whoa, chill out!' if you yell at him.
    #He says 'Fine. Be that way!' if you address him without actually saying
    #anything.
    #He answers 'Whatever.' to anything else.
    if  len(what.strip()) == 0:
        answer = 'Fine. Be that way!'
    elif what.isupper():
        answer = 'Whoa, chill out!'
    elif '?' == what.rstrip()[-1]:
        answer = 'Sure.'
    else:
        answer = 'Whatever.'


    return answer
