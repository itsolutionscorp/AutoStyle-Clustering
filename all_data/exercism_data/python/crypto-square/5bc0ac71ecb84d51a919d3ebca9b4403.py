__author__ = 'emiller42'
import string
import math


def encode(message):
    exclude_list = set((string.whitespace + string.punctuation))
    normalized_message = ''.join(ch.lower() for ch in message if ch not in exclude_list)
    columns = int(math.ceil(math.sqrt(len(normalized_message))))

    encoded_message = ''

    for current_column in range(0, columns):
        if current_column > 0:
            encoded_message += ' '
        encoded_message += ''.join(normalized_message[i]
                                   for i in range(current_column, len(normalized_message), columns))

    return encoded_message
