def hey(convo =''):
    """Function trying to mimic bob the lackadaisical teenager(but, after all he's a human and this is
     just a machine ;)."""

    #Seems like strip function gets rid of all the whitespaces and so any kind of silence is sorted out.
    if convo.strip() == u'':
        return 'Fine. Be that way!'

    #If everything told to bob(if the speech includes characters) is in 'high pitch'(capitals),
    # bob recognizes it as yelling.
    elif any(character.isalpha() for character in convo) and convo.isupper():
        return 'Woah, chill out!'

    #If the last character is a question mark, bob thinks its a question(in case,
    #its not a yelling).
    elif convo.endswith('?'):
        return 'Sure.'

    #Nothing to comment about this, just the classical reply.
    else:
        return 'Whatever.'
