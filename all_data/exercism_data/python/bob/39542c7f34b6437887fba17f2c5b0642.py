
def hey(what):
  
    if what.rstrip()=="":
        return 'Fine. Be that way!'

    if what[0].isdigit():
        if what[-1]=="?":
            return 'Sure.'
        elif what[-1]=="!":
            return 'Whoa, chill out!'
        else:
            return 'Whatever.'
    
    elif what == what.upper():        
        return 'Whoa, chill out!'
    
    elif what.rstrip()[-1]=="?":       
        return 'Sure.'

    else:
        return 'Whatever.'
       
