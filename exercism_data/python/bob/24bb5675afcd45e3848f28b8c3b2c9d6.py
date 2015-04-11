def hey(comment):
    if len(comment) == 0 or comment.isspace():
        return "Fine. Be that way!"
    
    if comment.isupper():
        return "Whoa, chill out!"

    if comment[-1] == '?':
        return "Sure."

    return "Whatever."
