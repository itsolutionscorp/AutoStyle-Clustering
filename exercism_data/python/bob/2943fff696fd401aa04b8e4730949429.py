def hey(convo=""):
    """Function trying to mimic bob the lackadaisical teenager(but, afterall he's a human and this is just a machine ;)."""

    #Seems like strip function gets rid of all the whitespaces and so any kind of silence is sorted out.
    if convo.strip()==u'':
        return('Fine. Be that way!')

    #If everything told to bob(if the speech includes characters) is in 'high pitch'(capitals), bob recognizes it as yelling.
    elif convo.upper()==convo and len([character for character in convo if character.isalpha()])>0:
        return('Woah, chill out!')

    #If the last character is a question mark, bob thinks its a question(in case
    #it's not a yelling).
    elif convo[-1]=='?':
        return('Sure.')

    #Nothing to comment about this, just the classical reply.
    else:
        return('Whatever.')

	
