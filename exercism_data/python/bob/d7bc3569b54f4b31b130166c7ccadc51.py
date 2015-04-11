#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    sentence= ''

    if what.replace(" ","")[-1:] == '?' and what.isupper() is False:
	sentence = 'Sure.'
    elif what.isupper():
        sentence = 'Whoa, chill out!'
    elif what == '' or ''.join(what.split()) == '':
        sentence = 'Fine. Be that way!'
    else:
        sentence = 'Whatever.'    
    return sentence
