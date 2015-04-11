##BOB MODULE

def hey(string_in):
    if not string_in.strip():
        return "Fine. Be that way!"
    elif string_in.upper() == string_in and not (string_in[:-1].isdigit()):
        if string_in[0].isdigit() and string_in[-1] != '!':
            return "Whatever."
        else:
            return "Whoa, chill out!"
    elif string_in[-1] == "?":
        return "Sure."
#    elif not string_in:
#        return "Fine. Be that way!"
    else:
        return "Whatever."
