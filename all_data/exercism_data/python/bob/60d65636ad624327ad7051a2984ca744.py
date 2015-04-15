def hey(s):
    response = "Whatever."

    if s.isupper():
        response = "Whoa, chill out!"
    elif not s.strip():
        response = "Fine. Be that way!"
    elif s[-1] == '?':
        response = "Sure."
    
    return response
