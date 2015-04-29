chars = [('I', 'V'), ('X', 'L'), ('C', 'D'), ('M', '?'), ('?', '?')]

def numeral(arabic):
    places = [arabic % 10**(x+1) / 10**x for x in range(0, len(str(arabic)))]
    final = []

    for place in range(0, len(places)):
        pchars = [chars[place][0], chars[place][1], chars[place+1][0]]
        pstr = []

        if places[place] == 9:
            pstr = [pchars[0], pchars[2]]
        elif places[place] == 4:
            pstr = [pchars[0], pchars[1]]
        elif places[place] >= 5:
            pstr = [pchars[1], pchars[0]*(places[place]-5)]
        else:
            pstr = pchars[0]*places[place]

        final.insert(0, ''.join(pstr))

    return ''.join(final)
