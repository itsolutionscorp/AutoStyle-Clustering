#
# Skeleton file for the Python "Bob" exercise.
#
def hey(command):
    """

    :type command: object
    """
    if isinstance(command, basestring):
        command.replace(' ', '')
        if command.isupper():
            return 'Whoa, chill out!'
        if command.endswith('?'):
            return 'Sure.'
        elif len(command.split()) == 0:
            return 'Fine. Be that way!'
        else:
            return 'Whatever.'

    return 'Whatever.'
