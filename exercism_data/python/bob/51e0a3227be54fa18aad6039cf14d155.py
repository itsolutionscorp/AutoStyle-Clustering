def hey(quote):
    if quote.isspace() or len(quote) == 0:
        return 'Fine. Be that way!'  
    #elif quote[len(quote)-1] == '?' and quote.isupper()==False:
    elif quote.endswith("?") and quote.isupper() == False:
        return 'Sure.'
    elif quote.isupper():
        return 'Whoa, chill out!'
    else:
        return 'Whatever.'
