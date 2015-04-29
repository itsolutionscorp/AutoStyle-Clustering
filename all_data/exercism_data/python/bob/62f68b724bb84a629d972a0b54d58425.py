import re
def hey(statement):
    """Analyzes statement text and returns Bob's response"""

    ## Trim whitespace, as it's not meaningful in this context.
    statement = statement.strip()

    ## A statement with all caps and at least one roman letter is yelling.
    if statement == statement.upper() and re.findall('[a-zA-Z]', statement):                    
        return 'Woah, chill out!'
    
    ## A statement terminated by a question mark is a question.
    if re.findall('\?$', statement):                    
        return 'Sure.'
    
    ## A statement without any non-whitespace characters is an empty string.
    if statement == '':                                     
        return 'Fine. Be that way!'

    ## Bob's default response
    return 'Whatever.'
