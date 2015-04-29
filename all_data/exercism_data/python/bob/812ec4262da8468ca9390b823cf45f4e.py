def hey(string):
    if string.isupper():
        return "Whoa, chill out!";
    if string.endswith("?"):
        return "Sure.";
    if string == "" or string.isspace():
        return "Fine. Be that way!";
    else:
        return "Whatever.";
