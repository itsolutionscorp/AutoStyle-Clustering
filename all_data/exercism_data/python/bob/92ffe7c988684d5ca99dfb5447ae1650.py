def hey(request):

    if request.strip()=='':
        #addressing him without actually saying anything
        response = 'Fine. Be that way!'
    else:
        if request.isupper():
            #if you are shouting then chill out
            response = 'Whoa, chill out!'
        elif request[-1]=='?':
            #if it's a request then sure
            response = 'Sure.'
        else:
            #otherwise whatever
            response = 'Whatever.'
            
    return response

    
