#
# Skeleton file for the Python "Bob" exercise.
#
#def hey(what):
#
#    return

#Una clase para valorar las variantes insertadas y retornar la respuesta de bob
class Bob(object):
    def hey(_, input):
    if input is None or input.strip() == '':
        return 'Fine. Be that way!'
    if input.isupper():
        return 'Woah, chill out!'
    if input.endswith('?'):
        return 'Sure.'

    return 'Whatever.'
