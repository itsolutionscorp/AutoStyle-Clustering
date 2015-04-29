def hey(remark):
    #first we strip the remark from unnecessary spaces.
    remark = remark.strip(' \t\n\r')
   
   #then we give a response to the given remark
    if remark == "":                                                                            
     return "Fine. Be that way!"
    
    elif remark.isupper():
        return "Whoa, chill out!"
    
    elif remark[-1] == '?':
        return "Sure." 
    
    else:
        return "Whatever."
