#
# Skeleton file for the Python "Bob" exercise.
#
def hey(message):
        if message is None or message.strip() == '':
            return 'Fine. Be that way!'
        if message.isupper():
            return 'Woah, chill out!'
        if message.endswith('?'):
            return 'Sure.'
        return 'Whatever.'
