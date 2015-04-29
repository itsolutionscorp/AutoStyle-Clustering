dc = {
    0 : 'On the %s day of Christmas my true love gave to me',
    1 : '%sa Partridge in a Pear Tree.',
    2 : ' two Turtle Doves',
    3 : ' three French Hens',
    4 : ' four Calling Birds',
    5 : ' five Gold Rings',
    6 : ' six Geese-a-Laying',
    7 : ' seven Swans-a-Swimming',
    8 : ' eight Maids-a-Milking',
    9 : ' nine Ladies Dancing',
    10 : ' ten Lords-a-Leaping',
    11 : ' eleven Pipers Piping',
    12 : ' twelve Drummers Drumming'
    }
days = {
    1 : 'first',
    2 : 'second',
    3 : 'third',
    4 : 'fourth',
    5 : 'fifth',
    6 : 'sixth',
    7 : 'seventh',
    8 : 'eighth',
    9 : 'ninth',
    10 : 'tenth',
    11 : 'eleventh',
    12 : 'twelfth'
    }
def verse(am_days):
    #on the first/second/.. day bla bla
    stra = dc[0] % (days[am_days],) + ','
    #all the interim words
    for i in range(am_days,1,-1):
        stra += (dc[i])+','
    if am_days>1:
        return stra + (dc[1]) % (' and ',) + '\n'
    else:
        return stra + (dc[1]) % (' ',) + '\n'

def verses(mind,maxd):
    stra = ''
    for i in range(mind,maxd+1):
        stra+=verse(i) + '\n'
    return stra

def sing():
    return verses(1,12)
