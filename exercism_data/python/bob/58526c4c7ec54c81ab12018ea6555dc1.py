
def hey(request):

    if request == "" or request.isspace():
        return 'Fine. Be that way!'
    if request.isupper():
        return 'Whoa, chill out!'
    if request[-1] == '?':
        return "Sure."
        
    return "Whatever."
