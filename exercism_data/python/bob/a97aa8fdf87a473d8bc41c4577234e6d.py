#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    word = what.replace(" ","")
    # Case 1: yell at him, response 'Whoa, chill out!'
    if word.isupper():
        response = 'Whoa, chill out!'
    # Case 2: a question response 'Sure'
    elif word.endswith('?'):
        response = 'Sure.'
    # Case 3: address him without saying anything, response 'Fine. Be that way!'
    elif len(word) <= 1:
        response = 'Fine. Be that way!'
    # Case 4: for anything else, 'Whatever'
    else:
        response = 'Whatever.'
    return response
