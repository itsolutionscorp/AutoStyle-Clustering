def hey(request):

    response = ''
    
    if request.strip()=='':
        #addressing him without actually saying anything
        response = 'Fine. Be that way!'
    else:
        #check to see if there are any letters in the request
        any_letters = any([i.isalpha() for i in request])
        
        if request.upper()==request and any_letters:
            #if there are letters and you are shouting then chill out
            response = 'Whoa, chill out!'
        elif request[-1]=='?':
            #if it's a request then sure
            response = 'Sure.'
        else:
            #otherwise whatever
            response = 'Whatever.'
            
    return response

    
