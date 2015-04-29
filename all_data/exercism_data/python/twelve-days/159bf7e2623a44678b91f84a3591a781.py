ordinal = ['first', 'second', 'third', 'fourth', 'fifth', 'sixth',
           'seventh', 'eighth', 'ninth', 'tenth', 'eleventh', 'twelfth']
phrase = ['On the %s day of Christmas my true love gave to me, ',
          'a Partridge in a Pear Tree.\n',
          'two Turtle Doves',
          'three French Hens',
          'four Calling Birds',
          'five Gold Rings',
          'six Geese-a-Laying',
          'seven Swans-a-Swimming',
          'eight Maids-a-Milking',
          'nine Ladies Dancing',
          'ten Lords-a-Leaping',
          'eleven Pipers Piping',
          'twelve Drummers Drumming']


def sing():
    return verses(1, 12)


def verse(index):
    fmt1 = phrase[0] % ordinal[index-1]
    if index > 1:
        fmt2 = ", ".join(phrase[index:1:-1])
        sentence = "{0}{1}, and {2}".format(fmt1, fmt2, phrase[1])
    elif index == 1:
        sentence = "{0}{1}".format(fmt1, phrase[1])
    return sentence


def verses(i, j):
    return '\n'.join(verse(i) for i in range(i, j+1)) + '\n'
