days_gifts = [("first", "a Partridge in a Pear Tree"),
              ("second", "two Turtle Doves"),
              ("third", "three French Hens"),
              ("fourth", "four Calling Birds"),
              ("fifth", "five Gold Rings"),
              ("sixth", "six Geese-a-Laying"),
              ("seventh", "seven Swans-a-Swimming"),
              ("eighth", "eight Maids-a-Milking"),
              ("ninth", "nine Ladies Dancing"),
              ("tenth", "ten Lords-a-Leaping"),
              ("eleventh", "eleven Pipers Piping"),
              ("twelfth", "twelve Drummers Drumming")]

def verse(number):
    
    lyrics = "On the " + days_gifts[number-1][0] + " day of Christmas my true love gave to me, "

    if number > 1:
        for day in range(number-1, 0, -1):
            lyrics = lyrics + days_gifts[day][1] + ", "
        lyrics = lyrics + "and "
    
    lyrics = lyrics + days_gifts[0][1] + ".\n"

    return lyrics

def verses(start, stop):
    return ''.join([verse(i) + '\n' for i in range(start, stop+1)])

def sing():
    return verses(1, 12)
