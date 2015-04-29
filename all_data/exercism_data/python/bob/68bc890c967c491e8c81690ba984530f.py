def hey(statement):
    # 'Yelling' is all upper-case
    if (statement.isupper()):
        return 'Whoa, chill out!'
    # Questions end with a '?'
    elif (statement.endswith('?')):
        return 'Sure.'
    # If you don't say anything, then after the white space is stripped,
    #   the statement's size is 0
    elif (len(statement.strip()) == 0):
        return 'Fine. Be that way!'
    # Everything else gets Bob's default 'Whatever.'
    else:
        return 'Whatever.'
