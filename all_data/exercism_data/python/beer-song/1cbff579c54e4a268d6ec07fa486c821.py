def song( *args ):
    (start_verse, stop_verse) = song_inputs( args )
    return "".join([ verse(N) + "\n"
                     for N in range( start_verse, stop_verse-1, -1 ) ])

def verse( N ):
    if N > 0:
        return bottle(N).capitalize() + " of beer on the wall, " + \
            bottle(N) + " of beer.\n" + \
            "Take " + pronoun(N) + " down and pass it around, " + \
            bottle(N-1) + " of beer on the wall.\n"
    if N == 0:
        return "No more bottles of beer on the wall, " + \
               "no more bottles of beer.\n" + \
               "Go to the store and buy some more, " + \
               "99 bottles of beer on the wall.\n"

def bottle( N ):
    if N > 1:
        return str(N) + " bottles"
    elif N == 1:
        return "1 bottle"
    elif N == 0:
        return "no more bottles"

def pronoun( N ):
    return { True: "one",
             False: "it" }[ N > 1 ]

def song_inputs( args ):
    start_verse = 99
    stop_verse = 0
    if len( args ) == 1:
        start_verse = args[0]
    if len( args ) > 1:
        start_verse = args[0]
        stop_verse = args[1]
    if start_verse <= stop_verse:
        raise ValueError( "Can't stop before you start!" )
    return (start_verse, stop_verse)
