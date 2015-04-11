def verse(num):
    nth = {
        1: "first",
        2: "second",
        3: "third",
        4: "fourth",
        5: "fifth",
        6: "sixth",
        7: "seventh",
        8: "eighth",
        9: "ninth",
        10: "tenth",
        11: "eleventh",
        12: "twelfth"
    }
    
    words = [
        'a Partridge in a Pear Tree.',
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
        'twelve Drummers Drumming, '
    ]
    
    result = 'On the ' + nth[num] + ' day of Christmas my true love gave to me, '
    if num > 1:
        result += ''.join([words[i] for i in reversed(range(1,num))]) + "and "
    result += words[0] + '\n'
    return result

def verses(num1, num2):
    return '\n'.join([verse(i) for i in range(num1, num2 + 1)]) + '\n'

def sing():
    return verses(1, 12)
