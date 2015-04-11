import os, sys

def hey(statement):
    real_letter = 0

    #check each value in string to ensure at
    #least one letter or number is present
    for a in statement:
        if a.isalpha() or a.isalnum():
            real_letter = 1
    #if there are no alpha numeric chars, assume empty
    if not real_letter:
        return "Fine. Be that way!"
    #check for all upper case
    if statement.isupper():
        return "Whoa, chill out!"
    #if it ends in a question mark
    if statement[-1] == '?':
        return 'Sure.'
    return "Whatever."
