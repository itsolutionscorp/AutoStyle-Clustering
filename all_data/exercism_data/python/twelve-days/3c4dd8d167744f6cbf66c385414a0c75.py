from collections import OrderedDict as OD

lines = [
         (1,"and a Partridge in a Pear Tree.\n"), (2,"two Turtle Doves"),
         (3,"three French Hens"), (4,"four Calling Birds"), 
         (5,"five Gold Rings"), (6,"six Geese-a-Laying"),
         (7,"seven Swans-a-Swimming"), (8,"eight Maids-a-Milking"),
         (9,"nine Ladies Dancing"), (10,"ten Lords-a-Leaping"),
         (11,"eleven Pipers Piping"), (12,"twelve Drummers Drumming")
         ]
         
cardi = {
         '1':'first', '2':'second', '3':'third', '4':'fourth', '5':'fifth',
         '6':'sixth', '7':'seventh', '8':'eighth', '9':'ninth', '10':'tenth',
         '11':'eleventh', '12':'twelfth'
         }
 
def verse(n):
    start = "On the {0} day of Christmas my true love gave to me, "\
            .format(cardi.get(str(n)))
    if n == 1:
        return start + "a Partridge in a Pear Tree.\n"
    add_line = ', '.join([v for k, v in 
                            sorted(lines, reverse=True) if k <= n])
    return start + add_line
    
def verses(start, finish):
    return '\n'.join([verse(i) for i in range(start, finish+1)]) + '\n'
    
def sing():
    return verses(1, 12)
