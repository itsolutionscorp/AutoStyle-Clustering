# somewhat clunky checking of capitalization/punctuation at the moment, but
# should remain functional for a large degree of cases.

def hey(string):
    words=string.rstrip('!?.123456789').split(' ')
    cflag = 1
    nflag = 0
    for w in words:
        if (w.isupper()==False):
            cflag = 0
        if (w.isupper()==True):
            nflag = 1
        if ((w.isdigit()==True) or (w.isalnum()==False)):
            cflag = 1
    if not string:
        response='Fine. Be that way!'
    elif (cflag==1 and nflag==1):
        response='Whoa, chill out!'
    elif string.endswith('?'):
        response='Sure.'
    elif string.isspace():
        response='Fine. Be that way!'
    else:
        response='Whatever.'
    return response
