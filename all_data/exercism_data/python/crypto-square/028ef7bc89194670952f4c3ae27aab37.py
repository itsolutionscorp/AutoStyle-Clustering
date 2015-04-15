def encode(message):
    message = ''.join([i for i in message.lower() if i.isalpha() or i.isdigit()])
    if message == '': return ''
    square_length = len(message) ** 0.5
    square = []
    while square_length > 0:
        square.append([])
        square_length -= 1
    for lnum in range(len(message)):
        square[lnum % len(square)].append(message[lnum])
    result = []
    for column in square:
        result.append(''.join(column))
    result = list(''.join(result))
    k = 1
    while k < len(result):
        if k % 5 == 0:
            result.insert(k + k / 5 - 1, ' ')
        k+=1
    while result[len(result) - 1] == ' ':
        del result[len(result) - 1]
    return ''.join(result)

"""
def decode(message):
    message = ''.join([i for i in message.lower() if i.isalpha() or i.isdigit()])
    if message == '': return ''
    mlen = len(message)
    square_length = mlen ** 0.5
    square = []
    while square_length > 0:
        square.append([])
        square_length -= 1
    for lnum in range(len(message)):
        square[int(lnum / round(len(message) / float(len(square))))].append(message[lnum])
    result = []
    print square
    for row in range(int(round(len(message) / float(len(square))))):
        for column in square:
            if len(column) > row:
                result.append(column[row])
    return ''.join(result)"""

def decode(message):
    message = [i for i in message.lower()]
    if message == '': return ''
    square_length = len(message) ** 0.5
    square = []
    while square_length > 0:
        square.append([])
        square_length -= 1
    for lnum in range(len(message)):
        square[lnum % len(square)].append(message[lnum])
    result = []
    for column in square:
        result.append(''.join(column))
    result = list(''.join(result))
    return ''.join(result)
