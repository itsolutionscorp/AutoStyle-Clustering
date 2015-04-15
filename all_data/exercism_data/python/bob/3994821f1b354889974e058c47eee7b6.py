def hey(statement):
    """Analyzes statement text and returns Bob's response"""

    ## Trim leading and trailing whitespace, as it's not meaningful in this context.
    statement = statement.strip()

    ## An upper-case statement is yelling.
    if statement.isupper():                    
        return 'Woah, chill out!'
    
    ## A statement terminated by a question mark is a question.
    if statement.endswith('?'):                    
        return 'Sure.'
    
    ## A statement without any non-whitespace characters is an empty string.
    if statement == '':                                     
        return 'Fine. Be that way!'

    ## Bob's default response
    return 'Whatever.'
