##BOB MODULE

def hey(string_in):
    if not string_in.strip():
        return "Fine. Be that way!"
    elif string_in.isupper():#string_in.upper() == string_in and not (string_in[:-1].isdigit()):
        return "Whoa, chill out!"
    elif string_in[-1] == "?":
        return "Sure."
    else:
        return "Whatever."
