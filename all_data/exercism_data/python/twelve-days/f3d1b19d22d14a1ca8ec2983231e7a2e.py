days = { 1:('first',    'a Partridge in a Pear Tree.\n'),
         2:('second',   'two Turtle Doves, and '),
         3:('third',    'three French Hens, '),
         4:('fourth',   'four Calling Birds, '),
         5:('fifth',    'five Gold Rings, '),
         6:('sixth',    'six Geese-a-Laying, '),
         7:('seventh',  'seven Swans-a-Swimming, '),
         8:('eighth',    'eight Maids-a-Milking, '),
         9:('ninth',    'nine Ladies Dancing, '),
        10:('tenth',    'ten Lords-a-Leaping, '),
        11:('eleventh', 'eleven Pipers Piping, '),
        12:('twelfth',  'twelve Drummers Drumming, '),
    }


def verse(versenum):
    nth_day, item = days[versenum]
    lyric  = "On the " + nth_day
    lyric += " day of Christmas my true love gave to me, " + item
    lyric += ''.join([days[i][1] for i in xrange(versenum - 1,0,-1)])
    
    return lyric

def verses(first_day = 1, last_day = 12):
    return ''.join([verse(i) + '\n' for i in xrange(first_day, last_day + 1)])

def sing():
    return verses()
