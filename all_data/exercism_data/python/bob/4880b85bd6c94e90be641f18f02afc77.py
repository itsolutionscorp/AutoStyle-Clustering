# coding: utf-8
import string

def hey(what):

    # Determine the length of the sentence
    lw = len(what)

    sentence = what
    d = 0
    l = 0
    L = 0

    # Set the default response from Bob
    response = "Whatever."

    # Loop through sentence characters
    for character in sentence:

        # Character is lowercase, or an "ä"
        if (character in string.lowercase) or (character in [u"ä"]):
            l += 1

        # Character is uppercase
        elif character in string.uppercase:
            L += 1

        # Character is digit
        elif character in string.digits:
            d += 1

    # No alphanumericals in sentence
    if (l==0) and (L==0) and (d==0):
        return "Fine. Be that way!"

    # Has no lowercase characters in sentence
    if (l==0) and (L>0):
        return "Whoa, chill out!"

    # Sentence contains lowercase letters and ends in ?
    if ((l>0) or (d>0)) and (what.endswith("?")==True):
        return "Sure."

    return response
