#
# Skeleton file for the Python "Bob" exercise.
#

def hey(stranger):

    if stranger.isupper():
        return 'Whoa, chill out!'

    if stranger.strip() == '':
        return 'Fine. Be that way!'

    if stranger.endswith('?'):
        return 'Sure.'

    if stranger.endswith(' '):
        return 'Sure.'

    else:
        return 'Whatever.'
