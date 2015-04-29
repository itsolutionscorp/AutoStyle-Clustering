# -*- coding: utf-8 -*-

import re

def hey(input):
    """Bob chatbot.
    
    Responds to an input unicode string with different sayings depending on the
    input.

    Args:
        input: An input unicode string

    Returns:
        A unicode string consistent with bob being a typical teenager
    """
    
    # using this string comparison was the most efficient Unicode compatible 
    # method I could find.
    yelling = input.upper() == input
    
    question = input[-1:] == "?"
    
    # silence is an input with only whitespace.  This should be compatible 
    # with unicode whitespace.
    silence = re.search("\S", input, re.U) is None
    
    # Only numbers are not yelling by themselves.  To test for this strip out 
    # all whitespace and punctuation and then look for a string which is nothing
    # but numbers.
    stripped = re.sub('[\s\,\.\?!]', "", input)
    only_numbers = re.search("[^0-9]", stripped, re.U) is None

    if silence:
        return "Fine. Be that way!"
    if yelling and not only_numbers:
        return "Whoa, chill out!"
    if question:
        return "Sure."
    return "Whatever."
if __name__ == '__main__':
    hey("")
