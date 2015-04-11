def hey(statement):

    # Clean any extraneous white space (otherwise a space after a question
    #   would return the wrong reply)
    statement = statement.strip()

    # If you don't say anything, then after the white space is stripped,
    #   the statement's size is 0
    if not statement:
        return 'Fine. Be that way!'
    
    # 'Yelling' is all upper-case
    elif (statement.isupper()):
        return 'Whoa, chill out!'
    
    # Questions end with a '?'
    elif (statement.endswith('?')):
        return 'Sure.'
        
    # Everything else gets Bob's default 'Whatever.'
    else:
        return 'Whatever.'
