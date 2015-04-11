''' Impementation of bob.py - the first exercise on exercism.io

.. module:: bob.py
    :platforms: All
    :synopsis: Implementation of bob

.. moduleauthor:: Keith Gray <idahogray@gmail.com>

'''

class Bob(object):
    ''' Implementation of Bob

    '''

    def hey(self, phrase):
        ''' Bob responds according to README.md when spoken to

        '''
        if phrase.strip() == '':
            return 'Fine. Be that way!'
        elif phrase.endswith('!') and phrase.isupper() or phrase.isupper():
            return 'Woah, chill out!'
        elif phrase.endswith('?'):
            return 'Sure.'

        return 'Whatever.'
