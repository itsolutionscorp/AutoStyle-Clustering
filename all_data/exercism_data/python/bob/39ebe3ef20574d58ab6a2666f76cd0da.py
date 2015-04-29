#-------------------------------------------------------------------------------
# Name:        Bob
# Purpose:     Determines how Bob will answer.
#
# Author:      Mihaela Antonescu
#
# Created:     23.09.2014
#-------------------------------------------------------------------------------

def hey(input_line):
    if(input_line.isupper()):
         # The person yelled at Bob
         return 'Whoa, chill out!'
    elif input_line.endswith('?'):
         # This is a question
         return 'Sure.'
    else:
         if input_line.isspace() or (input_line == ''):
            return 'Fine. Be that way!'
         else:
              return 'Whatever.'
