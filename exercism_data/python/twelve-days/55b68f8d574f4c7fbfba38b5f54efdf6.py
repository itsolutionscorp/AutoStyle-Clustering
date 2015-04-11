def sing():
    return verses( 1, 12 )

def verses( smaller, larger ):
    return ''.join([ verse(ii)+"\n" for ii in range(smaller,larger+1) ])

def verse( N ):
    return  "On the " + day(N) + " day of Christmas " + \
            "my true love gave to me, " + \
            "".join([ gift(ii,N) for ii in range(N,0,-1) ])

def gift( ii, N ):
    if ii == 1 and N == 1:
        return "a Partridge in a Pear Tree.\n"
    return {1:"and " + gift(1,1),
            2:"two Turtle Doves, ",
            3:"three French Hens, ",
            4:"four Calling Birds, ",
            5:"five Gold Rings, ",
            6:"six Geese-a-Laying, ",
            7:"seven Swans-a-Swimming, ",
            8:"eight Maids-a-Milking, ",
            9:"nine Ladies Dancing, ",
            10:"ten Lords-a-Leaping, ",
            11:"eleven Pipers Piping, ",
            12:"twelve Drummers Drumming, "}[ ii ]

def day( N ):
    return {1:"first",
            2:"second",
            3:"third",
            4:"fourth",
            5:"fifth",
            6:"sixth",
            7:"seventh",
            8:"eighth",
            9:"ninth",
            10:"tenth",
            11:"eleventh",
            12:"twelfth"}[ N ]
