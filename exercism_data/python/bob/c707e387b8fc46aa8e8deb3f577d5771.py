#
# Skeleton file for the Python "Bob" exercise.
#

import unicodedata
import types
"""
Bob is a lackadaisical teenager. In conversation, his responses are very limited.

Bob answers 'Sure.' if you ask him a question.

He answers 'Whoa, chill out!' if you yell at him.

He says 'Fine. Be that way!' if you address him without actually saying
anything.

He answers 'Whatever.' to anything else.

"""

def is_uppercase(statement):
    """ 
    Input: a text string, either a String type or a Unicode type.
    Output: True, if 
            (1) there are letters that can be capitalized in the 
                statement, and 
            (2) all those letters are capitalized. 
            False otherwise.
    """
    if type(statement) is types.StringType:
      return statement.isupper()
    else:
      types_of_characters = set([])
      uppercase = 'Lu'
      lowercase = 'Ll'
      for char in statement:
        types_of_characters.add(unicodedata.category(char))
      if lowercase in types_of_characters or uppercase not in types_of_characters:
        return False
      return True
    
      
def hey(statement):

    #Strip beginning and trailing whitespaces
    statement = statement.strip()

    if len(statement)==0:
      return 'Fine. Be that way!'

    if is_uppercase(statement):
      return 'Whoa, chill out!'

    # Note: Should the sentence, "Can you hear me? My phone is broken." be
    # answered as a question? The test suite's test, "Ending with a ? means a
    # question" seems to imply the answer is no, so that's what I've assumed.
    if statement[-1]=='?':
      return 'Sure.'

    return 'Whatever.'.encode('ascii')
