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
         # result flag will indicate that at least one character
         # is not a space
         result = True
         for character in input_line:
            if not character.isspace():
               result = False
         if result == True:
            # The person does not say anything to Bob
            return 'Fine. Be that way!'
         else:
              return 'Whatever.'
