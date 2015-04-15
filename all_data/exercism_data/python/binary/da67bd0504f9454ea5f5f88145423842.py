import re

def parse_binary(num):
    if re.compile('[^01]').search(num):
        raise ValueError('non binary input')

    return sum(int(char) * 2 ** index for index, char in enumerate(reversed(num)))
