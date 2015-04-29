def hey(string):

    string = string.strip()
    # if you yell at him
    if string.isupper():
        return "Whoa, chill out!" 
    # if you ask him a question
    if string.endswith('?'):
        return "Sure."
    #if you say nothing
    if string == '':
        return "Fine. Be that way!"
    else:
        return "Whatever."
