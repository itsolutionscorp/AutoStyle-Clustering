def hey (input):
    
    if input == "" or input.isspace():
        return "Fine. Be that way!"

    elif input.isupper():
        return "Whoa, chill out!"

    elif input[len(input)-1] == "?":
        return "Sure."
    
    
    else :
        return "Whatever."
    
            
