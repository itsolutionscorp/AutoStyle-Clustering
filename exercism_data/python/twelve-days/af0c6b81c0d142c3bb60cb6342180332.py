def sing():
    return verses(1, 12)


def verse(index):
    list12 = ['first', 'second', 'third', 'fourth', 'fifth', 'sixth',
              'seventh', 'eighth', 'ninth', 'tenth', 'eleventh', 'twelfth']
    phrase = ['On the %s day of Christmas my true love gave to me, ',
              'a Partridge in a Pear Tree.\n',
              'two Turtle Doves, ',
              'three French Hens, ',
              'four Calling Birds, ',
              'five Gold Rings, ',
              'six Geese-a-Laying, ',
              'seven Swans-a-Swimming, ',
              'eight Maids-a-Milking, ',
              'nine Ladies Dancing, ',
              'ten Lords-a-Leaping, ',
              'eleven Pipers Piping, ',
              'twelve Drummers Drumming, ']

    ret = []
    for i in range(0, 12):
        sentence = phrase[0] % list12[i]
        for j in range(i+1, 0, -1):
            if i > 0 and j == 1:
                sentence += 'and '
            sentence += phrase[j]
        ret.append(sentence)

    return ret[index-1]


def verses(i, j):
    ret = ''
    for index in range(i, j+1):
        ret += verse(index)
        ret += '\n'
    return ret
