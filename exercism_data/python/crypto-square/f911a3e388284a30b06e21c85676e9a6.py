#!/usr/bin/python
from string import lowercase, digits
from math import sqrt, ceil
def sanitised(phrase):
    result = ""
    phrase = phrase.lower().replace(" ", "")
    for char in phrase:
        if char in (lowercase + digits):
            result += char
    return result

def get_square_side_length(phrase):
    length = len(phrase)
    return  int(ceil(sqrt(length)))

def split_into_rows(side_length, phrase):
    rows = []
    for interval in xrange(0, len(phrase), side_length):
        start = interval
        end = interval + side_length
        rows.append(phrase[start:end])
    return rows

def read_columns(phrase):
    result = []
    number_of_columns = len(phrase[0])
    number_of_rows = len(phrase)
    for column in xrange(number_of_columns):
        for row in xrange(number_of_rows):
            next_column = column+1
            result.append(phrase[row][column:next_column])
    return result

def split_into_runs_and_concatinate(phrase):
    return " ".join(split_into_rows(5, "".join(phrase)))

def encode(phrase):
    phrase = sanitised(phrase)
    if len(phrase) == 0:
        return ""
    phrase = split_into_rows(get_square_side_length(phrase), phrase)
    phrase = read_columns(phrase)
    return split_into_runs_and_concatinate(phrase)

def intervals(phrase):
    side_length = get_square_side_length(phrase)
    difference = side_length**2 - len(phrase) - side_length
    interval_list = [side_length] * side_length
    for index, length in enumerate(interval_list):
        interval_list[index] = (index, length - 1)
    for index, length in interval_list:
        if index > length - difference:
            interval_list[index] = length - 1
        else:
            interval_list[index] = length
    interval_ranges = []
    for index in xrange(len(interval_list)):
        interval_ranges.append(sum(interval_list[:index]))
    interval_ranges.append(sum(interval_list))
    start_indexes = interval_ranges[:-1]
    end_indexes = interval_ranges[1:]
    interval_ranges = zip(start_indexes, end_indexes)
    result = []
    for start, end in interval_ranges:
        result.append(phrase[start:end])
    return result

def decode(phrase):
    phrase = sanitised(phrase)
    phrase = intervals(phrase)
    phrase = read_columns(phrase)
    phrase = sanitised("".join(phrase))
    return phrase
