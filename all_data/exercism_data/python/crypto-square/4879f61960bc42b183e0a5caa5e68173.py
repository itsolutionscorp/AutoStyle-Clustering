import math


def encode(message):
    normalized = [c.lower() for c in message if c.isalnum()]
    columns = _determine_columns(len(normalized))

    output = [[] for _ in range(columns)]
    for index, c in enumerate(normalized):
        output[index % columns].append(c)

    return ' '.join(''.join(c) for c in output)


def _determine_columns(size):
    return int(math.ceil(math.sqrt(size)))
