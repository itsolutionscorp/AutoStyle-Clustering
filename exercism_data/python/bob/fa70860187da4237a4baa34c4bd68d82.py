def hey(remark):
    #strip the remark from unnecessary spaces.
    remark = remark.strip()
    
    #if empty
    if remark == "":                                                                            
     return "Fine. Be that way!"
   
    #if shout 
    elif remark.isupper():
        return "Whoa, chill out!"
    
    #if question
    elif remark.endswith('?'):
        return "Sure." 
    
    #default
    return "Whatever."
