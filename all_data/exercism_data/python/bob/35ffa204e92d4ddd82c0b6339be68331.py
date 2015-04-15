def hey(greeting):

    # Yell, if everything is uppercase
    if greeting.isupper():
        return "Woah, chill out!"

    # Check if the greeting contains only whitespace
    elif len(greeting.split()) == 0:
        return "Fine. Be that way!"

    # Questions end in question mark
    elif greeting[-1] == "?":
        return "Sure."

    # Everything else
    else:
        return "Whatever."
