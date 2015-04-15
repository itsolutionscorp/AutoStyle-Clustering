def hey(input):
        #only whitespace
    if (len(input.strip()) == 0): 
        return 'Fine. Be that way!'

        #AT LEAST one alphabetical character, and it is UPPERCASE
    elif (input == input.upper() and len([x for x in input if x.isalpha()]) > 0): #if input counts as 'shouting'
        return 'Whoa, chill out!'

        #ends in ? and doesn't trigger uppercase-only clause
    elif (input[-1] == '?' ):
        return 'Sure.'

    else:
        return 'Whatever.'
