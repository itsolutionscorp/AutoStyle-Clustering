def hey(input):

    if input.strip() == '':
        return 'Fine. Be that way!'
    elif input.isupper():
        return 'Woah, chill out!'
    elif len(input) > 0 and input[-1] == '?':
        return 'Sure.'    
    else:
        return 'Whatever.'
