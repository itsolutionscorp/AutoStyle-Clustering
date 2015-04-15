def hey(what):

    if what.split()==[]:  
        return 'Fine. Be that way!'
    
    elif what==what.upper() and what!=what.lower():
        return 'Whoa, chill out!'
    
    elif what[len(what)-1]=='?':
        return 'Sure.'

    return 'Whatever.'
