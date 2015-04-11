# Returns Bob's response to the given string
def hey(string):
    if isYelling(string):
        return "Whoa, chill out!";

    if isQuestion(string):
        return "Sure.";

    if isSilence(string):
        return "Fine. Be that way!";

    # Anything else
    return "Whatever.";

# True if ends with ?
def isQuestion(string):
    return string.endswith("?");

# True if all uppercase
def isYelling(string):
    return string.isupper();

# True if all space or empty string
def isSilence(string):
    return string.isspace() or not string;
