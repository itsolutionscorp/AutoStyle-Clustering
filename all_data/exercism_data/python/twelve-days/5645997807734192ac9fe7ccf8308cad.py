__author__ = 'tracyrohlin'


"""def christmas_gifts(n):
    gifts = {1: " and a Partridge in a Pear Tree.\n", 2: " two Turtle Doves,", 3: " three French Hens,",
               4: " four Calling Birds,", 5: " five Gold Rings,", 6: " six Geese-a-Laying,",
               7: ' seven Swans-a-Swimming,', 8: " eight Maids-a-Milking,",
                9: " nine Ladies Dancing,", 10: ' ten Lords-a-Leaping,',
                11: ' eleven Pipers Piping,', 12: " twelve Drummers Drumming,"}
    new_verse = gifts[n]

    if n == 1:
        lyric = new_verse
    else:
        lyric = new_verse + verse(n-1)

    if n == 1: #gets rid of and when it is just the first day of christmas
        mystring = gifts[1]
        lyric = mystring.replace(" and", "")

    return lyric"""


"""def days_of_christmas(n):
    numbers = {1: "first", 2: "second", 3: "third", 4: "fourth", 5: "fifth", 6: "sixth", 7: "seventh", 8: "eighth",
               9: "ninth", 10: "tenth", 11: "eleventh", 12: "twelfth"}
    lyric = 'On the {0} day of Christmas my true love gave to me,'.format(numbers[n])
    return lyric"""

from test_recur import christmas_gifts
from test_first_verse import days_of_christmas

def verse(n):
    lyric = days_of_christmas(n) + christmas_gifts(n)
    if n == 1:
        mystring = lyric.replace(" and", "")
        return mystring
    return lyric

def verses(b, e):
    lyrics = ""
    for n in range(b,e+1):
        if verse(n)[-1] == "\n":
            lyric = verse(n) + "\n"
            lyrics += lyric
        else:
            lyric = verse(n) + "\n\n"
            lyrics += lyric

    return lyrics

def sing():
    return verses(1, 12)
