__author__ = 'jimblackler'


def convert(number, tens, fives, ones):
    return ['', ones, ones * 2, ones * 3, ones + fives, fives, fives + ones, fives + ones * 2, fives + ones * 3,
            ones + tens][number]


def numeral(arabic):
    return ('M' * (arabic / 1000) +
            convert(arabic % 1000 / 100, 'M', 'D', 'C') +
            convert(arabic % 100 / 10, 'C', 'L', 'X') +
            convert(arabic % 10, 'X', 'V', 'I'))
