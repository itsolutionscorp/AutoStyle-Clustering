#!/usr/bin/python
def hey(s_statement):
    #string short hand
    fine = "Fine. Be that way!"
    whoa = "Whoa, chill out!"
    sure = "Sure."
    whatever = "Whatever."
    
    if len(s_statement.strip()) == 0: # if the statement, after stripping whitespace has zero length
        return fine
    s_statement = s_statement.strip() # remove pre & trailing whitespace
    if s_statement.isupper():         # if the statement is all uppercase characters, non-alpha characters ignored
        return whoa
    if s_statement[-1] == "?":        # if the statement ends in a question mark
        return sure
    return whatever                   # all other cases
