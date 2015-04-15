__author__ = 'mandren'

class Bob():

    def hey(self, input_string):

        if len(input_string) == 0 or input_string.strip() == '':
            return 'Fine. Be that way!'

        elif input_string.isupper():
            return 'Woah, chill out!'

        elif input_string[-1] == '?':
            return 'Sure.'

        return 'Whatever.'

    pass

#  'Sure.'


