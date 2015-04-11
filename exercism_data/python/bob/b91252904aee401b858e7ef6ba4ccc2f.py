import os, sys

def hey(statement):
    statement = statement.strip()

    #is least one letter or number is present?
    if statement == '' :
        return "Fine. Be that way!"
    #check for all upper case
    if statement.isupper():
        return "Whoa, chill out!"
    #if it ends in a question mark
    if statement[-1] == '?':
        return 'Sure.'
    return "Whatever."
