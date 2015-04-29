def hey(chat):
 

    if chat.isupper():
        return("Whoa, chill out!")

    elif not chat:
        return("Fine. Be that way!")
        
    elif chat.isspace():
            return("Fine. Be that way!")

    else:
        for x in chat:
            last_character = chat[int(len(chat)-1)]

            if last_character == "?":
                return ("Sure.")

            else:
                return("Whatever.")
