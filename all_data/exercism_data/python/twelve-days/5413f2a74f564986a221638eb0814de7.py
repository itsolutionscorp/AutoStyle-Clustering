keys = ['first', 'second', 'third',
        'fourth', 'fifth', 'sixth',
        'seventh', 'eighth', 'ninth',
        'tenth', 'eleventh', 'twelfth']

values = [' a Partridge in a Pear Tree.', ' two Turtle Doves, and', ' three French Hens,',
          ' four Calling Birds,', ' five Gold Rings,', ' six Geese-a-Laying,',
          ' seven Swans-a-Swimming,', ' eight Maids-a-Milking,', ' nine Ladies Dancing,',
          ' ten Lords-a-Leaping,', ' eleven Pipers Piping,', ' twelve Drummers Drumming,']

d = dict(zip(keys, values))

def verse(num):
    base = "On the "+keys[num-1]+" day of Christmas my true love gave to me,"
    top = build_verse(num)
    verse = base + top + "\n"
    return verse

def build_verse(n):
    if n == 1:
        return values[0]
    else:
        return values[n-1] + build_verse(n-1)

def verses(start, stop):
    result = ""
    while start <= stop:
        result += verse(start)
        result += "\n"
        start += 1
    return result

def sing():
    return verses(1, 12)
