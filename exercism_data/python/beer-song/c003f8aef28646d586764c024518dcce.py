import inflect

def verse(number):
    if number == 0:
        return format_bottles(number).capitalize() + " on the wall, " + \
               format_bottles(number) + ".\n" + \
               "Go to the store and buy some more, " + \
               format_bottles(99) + " on the wall.\n"

    return format_bottles(number).capitalize() + " on the wall, " + \
           format_bottles(number) + take_string(number) + \
           format_bottles(number-1) + " on the wall.\n"

def format_bottles(number):
    p = inflect.engine()
    if number == 0:
        return p.number_to_words(number, zero='no more') + " " +\
               p.plural('bottle', number) + " of beer"
    return p.no('bottle', number) + " of beer"

def take_string(number):
    subs = "one"
    if number == 1:
        subs = "it"
    return ".\nTake " + subs + " down and pass it around, "

def song(verse_start, verse_end=0):
    result = ""
    for current in range(verse_start, verse_end-1, -1):
        result += verse(current) + "\n"
    return result
