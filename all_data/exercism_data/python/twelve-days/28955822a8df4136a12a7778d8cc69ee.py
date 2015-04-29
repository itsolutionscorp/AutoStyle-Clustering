gifts = [
    ("Partridge in a Pear Tree.", "and a"),
    ("Turtle Doves", "two"),
    ("French Hens", "three"),
    ("Calling Birds", "four"),
    ("Gold Rings", "five"),
    ("Geese-a-Laying", "six"),
    ("Swans-a-Swimming", "seven"),
    ("Maids-a-Milking", "eight"),
    ("Ladies Dancing", "nine"),
    ("Lords-a-Leaping", "ten"),
    ("Pipers Piping", "eleven"),
    ("Drummers Drumming", "twelve")
]

def sing():
    return verses(1, 12)
    
def verse(num):
    if num == 1:
        verse = on_the_nth_day(0) + ", a " + gifts[0][0] + "\n"   
        return verse
    return _verse(num) + "\n"

def verses(start,end):
    end = end + 1
    v = [] 
    for n in range(start,end):
        v.append(verse(n))
    return "\n".join(v) + "\n"

def _verse(end):
    verse = on_the_nth_day(end - 1)
    bag = [verse,]
    for gift in list(reversed(gifts[0:end])):
        bag.append(gift[1] + " " + gift[0])
    return ", ".join(bag)


def on_the_nth_day(n):
    num_in_word = ("first", "second", "third", "fourth", "fifth", "sixth",
    "seventh", "eighth", "ninth", "tenth", "eleventh", "twelfth")
    return "On the " + num_in_word[n] + " day of Christmas my true love gave to me"
