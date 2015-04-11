"""This module is a limited chat-bot that follows the specification in README.md

Bob is a lackadaisical teenager. In conversation, his responses are very limited.
Bob will answer the following classes of inputs:
** Questions:   Input that ends in a '?'
** Yelling:     Input that is in all capitals.
** Empty input: Input that includes only whitespace.
** Other:       All other input.
"""

def hey(sentence):
  """Function for interacting with Bob.

  Notes:
    ** Yelling: takes precedence over questions: an all-caps question is
                considered yelling.
    ** Empty input: None, 0-length string, and all whitespace strings are
                    considered empty inputs.

  Args:
    sentence: <string> A sentence used to interact with Bob.

  Returns:
    <string> Bob's response.
  """
  # Empty input.
  if not sentence or not len(sentence) or sentence.isspace():
    return 'Fine. Be that way!'
  # Yelling input.
  if sentence.isupper():
    return 'Whoa, chill out!'
  # Question input.
  if sentence[-1] == '?':
    return 'Sure.'
  # Other input.
  return 'Whatever.'
