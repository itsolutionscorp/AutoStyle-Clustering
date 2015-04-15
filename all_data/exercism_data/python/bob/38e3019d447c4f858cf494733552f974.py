def hey(string):
    if string == string.upper() and not string == string.lower():
        return "Whoa, chill out!";
    if string.endswith("?"):
        return "Sure.";
    if string == "" or string.isspace():
        return "Fine. Be that way!";
    else:
        return "Whatever.";
