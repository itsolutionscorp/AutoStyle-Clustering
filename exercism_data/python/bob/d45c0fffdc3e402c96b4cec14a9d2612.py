def hey(declaration):

    if declaration.isupper(): 
        return 'Whoa, chill out!'
    elif "?" in declaration and declaration.endswith("."):
        return 'Whatever.'
    elif "?" in declaration:
        return 'Sure.'
    elif declaration.isspace() or len(declaration.split()) == 0: 
        return 'Fine. Be that way!'
    else: 
        return 'Whatever.'
