'''A conversation with Bob.
'''
def hey(comment):
    '''Talk to Bob.
    '''
    if comment == '' or comment.isspace():
        return 'Fine. Be that way!'
    elif comment.isupper():
        return 'Whoa, chill out!'
    elif comment[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
