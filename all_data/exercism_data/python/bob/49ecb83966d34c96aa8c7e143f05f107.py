#
# Skeleton file for the Python "Bob" exercise.
	#

def hey(sentence):
    if not sentence.strip():
        return 'Fine. Be that way!'
    elif sentence.isupper():
        return 'Whoa, chill out!'
    elif sentence[-1] == '?':
        return 'Sure.'
    else: 
        return 'Whatever.'
