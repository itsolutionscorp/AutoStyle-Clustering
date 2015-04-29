def sing():
    return verses(1,12)

def verse(number):
    days = ["", "first", "second", "third", "fourth", "fifth", "sixth",
            "seventh", "eighth", "ninth", "tenth", "eleventh", "twelfth"]
    presents = ["", "a Partridge in a Pear Tree","two Turtle Doves",
                "three French Hens","four Calling Birds",
                "five Gold Rings","six Geese-a-Laying","seven Swans-a-Swimming",
                "eight Maids-a-Milking","nine Ladies Dancing",
                "ten Lords-a-Leaping","eleven Pipers Piping",
                "twelve Drummers Drumming"]

    result = "On the %s day of Christmas my true love gave to me, " % days[number]

    for i in range(number, 0, -1):
        if i == 1:
            if number != 1:
                result += "and "
            result += presents[i] + "."
        else:
            result += presents[i] + ", "
    return result + "\n"

def verses(first, last):
    result = ""
    for i in range(first, last+1):
        result += verse(i) + "\n"
    return result
