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
    elif input_line.strip() == "":
         # The person does not say anything
         return 'Fine. Be that way!'
    else:
         return 'Whatever.'
