def hey(argument):
    if all(char.isspace() for char in argument):
        return 'Fine. Be that way!'
    elif any(char.isalpha() for char in argument) and argument.upper() == argument:
        return 'Woah, chill out!'     
    elif argument.find('?') > -1 and argument.index('?') == len(argument) - 1:
        return 'Sure.'   
    else:
        return 'Whatever.'           
