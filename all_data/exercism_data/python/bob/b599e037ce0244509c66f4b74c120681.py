def hey(conversation):
    
    if conversation == "" or "\t" in conversation:
        return "Fine. Be that way!"
    if conversation.isupper():
        return "Whoa, chill out!"
    if conversation[-1] == "?":
        return "Sure."
    else :
        return "Whatever."
            
#bob = teenager()
