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
        return "%s, a %s\n" %(on_the_nth_day(0), gifts[0][0])
    return "%s\n" %(_verse(num))

def verses(start,end):
    return "\n".join([verse(n) for n in range(start,end+1)]) + "\n"

def _verse(end):
    end = end - 1
    return "%s, %s" %( on_the_nth_day(end),
                       ", ".join( ["%s %s" %(gift[1], gift[0]) for gift in gifts[end::-1]] ) )

def on_the_nth_day(n):
    return "On the %s day of Christmas my true love gave to me" %(num_in_word[n])
