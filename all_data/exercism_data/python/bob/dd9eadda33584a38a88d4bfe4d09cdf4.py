def hey(address):
    if(address == '' or address.isspace()):
        return 'Fine. Be that way!'
    elif(address.isupper()):
        return 'Whoa, chill out!'
    elif(address[-1] == '?'):
        return 'Sure.'
    else:
        return 'Whatever.'
