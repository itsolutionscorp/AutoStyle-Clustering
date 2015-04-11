def calculate( s ):

    if not ( s[0:8] == "What is " and s[-1] == "?" ):
        raise ValueError( "I can't read this question!" )
    else:
        s = s[8:len(s)-1]

    ss = s.split(" ")
    while len(ss) > 1:
        n = int(ss[0])
        if( ss[1] == "plus" ):
            ss[2] = str( n + int(ss[2]) )
            s = " ".join( ss[2:] )
        elif( ss[1] == "minus" ):
            ss[2] = str( n - int(ss[2]) )
            s = " ".join( ss[2:] )
        elif( ss[1] == "multiplied" and ss[2] == "by" ):
            ss[3] = str( n * int(ss[3]) )
            s = " ".join( ss[3:] )
        elif( ss[1] == "times" ):
            ss[2] = str( n * int(ss[2]) )
            s = " ".join( ss[2:] )
        elif( ss[1] == "divided" and ss[2] == "by" ):
            ss[3] = str( n // int(ss[3]) )
            s = " ".join( ss[3:] )
        else:
            raise ValueError( "I can't read your question!" )
        ss = s.split(" ")
    return int(s)
