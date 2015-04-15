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
num_in_word = ("first", "second", "third", "fourth", "fifth", "sixth",
"seventh", "eighth", "ninth", "tenth", "eleventh", "twelfth")

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
    end = end - 1
    for gift in gifts[end::-1]:
        bag.append(gift[1] + " " + gift[0])
    return ", ".join(bag)

def on_the_nth_day(n):
    return "On the " + num_in_word[n] + " day of Christmas my true love gave to me"
