import unicodedata
def hey(input):
    """

    Based on the input, reply with one of four responses.

    Check the criteria in order, and respond with the first
    response that matches, or "Whatever" if none match.

    "Fine. Be that way!"
      if the input is all whitespace

    "Whoa, chill out!" 
      if the string function isupper return True

    "Sure"
      if the input ends in a question mark

    "Whatever"
      for any other input
    """

    # Does input say nothing?
    # If input is all whitespace, strip() returns '',
    # which is boolean False.
    try:
        if not input.strip():
            return "Fine. Be that way!"
    except AtributeError: # Added test for input set to None
            return "Fine. Be that way!"

    # Is input yelling?
    # If input is all upper case, 
    if input.isupper():
        return u'Whoa, chill out!'

    # Is input a question?
    # If input ends in a question mark it is a queation
    if input.endswith("?"):
        return u'Sure.'

    # return the default response
    return u"Whatever."
