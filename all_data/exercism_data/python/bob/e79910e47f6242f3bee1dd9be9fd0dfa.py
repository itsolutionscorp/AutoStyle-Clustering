#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    """Returns Bob's answers, depending upon what has been said to him."""

    answer = ''
    bobs_answers = {'question': 'Sure.', 'yell': 'Whoa, chill out!',
                    'nothing said': 'Fine. Be that way!',
                    'anything else': 'Whatever.'}
    
    #remove beginning and ending whitespace
    what = what.strip()

    #ä ö ü

    if what == '':
        answer = bobs_answers['nothing said']
    elif what.isupper():
        answer = bobs_answers['yell']
    elif what[len(what) - 1] == '?':
        answer = bobs_answers['question']
    else:
        answer = bobs_answers['anything else']
        
    return answer


# ---- main
terminate = False

while not terminate:
    what = input('Talk to Bob (q to quit): ')
    
    if what != 'q':
        print('Bob\'s response:', hey(what))
    else:
        terminate = True
