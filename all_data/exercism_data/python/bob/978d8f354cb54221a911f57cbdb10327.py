def hey(chat):
 
    if chat.isupper():
        return("Whoa, chill out!")

    if not chat:
        return("Fine. Be that way!")
        
    if chat.isspace():
            return("Fine. Be that way!")

    if chat.endswith("?"):
            return ("Sure.")

    else:
        return("Whatever.")
