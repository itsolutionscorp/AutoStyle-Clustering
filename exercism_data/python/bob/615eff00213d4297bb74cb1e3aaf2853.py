def hey(input_string):
    
    # if input empty or spaces 
    if input_string.strip() == "":
        return "Fine. Be that way!" 
    
    # if this is in all caps / disregard non alphas
    elif any(i.isalpha() for i in input_string) and input_string.upper() == input_string:
        return "Woah, chill out!"
    
    # if this ends in a question mark
    elif input_string[-1] == '?':
        return "Sure."
    
    # default repsonse
    else:
        return "Whatever."
