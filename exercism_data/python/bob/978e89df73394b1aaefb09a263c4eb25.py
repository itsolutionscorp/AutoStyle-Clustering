
#Module for Bob the surly teenager

def hey(statement):
    
    if statement == statement.upper() and statement != statement.lower():
        return "Whoa, chill out!"
    elif not statement or statement.isspace():
        return "Fine. Be that way!"
    elif statement[-1] == '?':
        return "Sure."
    else:
        return "Whatever."

