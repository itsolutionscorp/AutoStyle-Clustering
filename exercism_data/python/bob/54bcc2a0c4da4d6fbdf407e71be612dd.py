#
# Skeleton file for the Python "Bob" exercise.
#
import types

"""
Bob is a lackadaisical teenager. In conversation, his responses are very limited.

Bob answers 'Sure.' if you ask him a question.

He answers 'Whoa, chill out!' if you yell at him.

He says 'Fine. Be that way!' if you address him without actually saying
anything.

He answers 'Whatever.' to anything else.

"""


      
def hey(statement):
    if type(statement) not in [types.StringType, types.UnicodeType]:
      return "Whatever."

    #Strip beginning and trailing whitespaces
    statement = statement.strip()

    if len(statement)==0:
      return 'Fine. Be that way!'

    if statement.isupper():
      return 'Whoa, chill out!'

    # Note: Should the sentence, "Can you hear me? My phone is broken." be
    # answered as a question? The test suite's test, "Ending with a ? means a
    # question" seems to imply the answer is no, so that's what I've assumed.
    if statement.endswith('?'):
      return 'Sure.'

    return 'Whatever.'
