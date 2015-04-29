'''
Created on 07/09/2013

@author: rodrigo
'''


class Bob(object):

    def hey(self, string):
        if string is None or not string.split():
            return 'Fine. Be that way!'
        else:
            if string.isupper():
                return 'Woah, chill out!'
            if string.endswith('?'):
                return 'Sure.'
            else:
                return 'Whatever.'
