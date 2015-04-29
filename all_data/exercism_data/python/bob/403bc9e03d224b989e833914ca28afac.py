def hey(input):
    """
    input: message sent to chatbot

    returns relevant response
    """

    #only whitespace
    if (len(input.strip()) == 0): 
        return 'Fine. Be that way!'

    #AT LEAST one alphabetical character, and it is UPPERCASE
    elif (input.isupper()):
        return 'Whoa, chill out!'

    #ends in ? and doesn't trigger uppercase-only clause
    elif (input[-1] == '?' ):
        return 'Sure.'

    else:
        return 'Whatever.'
