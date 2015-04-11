def hey(greeting):

    # Check if the greeting has lower or uppercase chars
    hasLower = False
    hasUpper = False
    for c in greeting:
        if c.islower():
            hasLower = True
        if c.isupper():
            hasUpper = True

    # Yell, if at least 1 uppercase char & no lowercase chars
    if hasUpper and not hasLower:
        return "Woah, chill out!"

    # Check if the greeting contains only whitespace
    elif len(greeting.split()) == 0:
        return "Fine. Be that way!"

    # Questions end in question mark
    elif greeting[len(greeting) - 1] == "?":
        return "Sure."

    # Everything else
    else:
        return "Whatever."
