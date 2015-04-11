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

def decode(message):
    message = [i for i in message.lower() if i.isalpha() or i.isdigit()]
    if message == '': return ''
    square_length = len(message) ** 0.5
    square = []
    while square_length > 0:
        square.append([])
        square_length -= 1
    for column in square:
        square_length = len(square)
        while square_length > 0:
            column.append('')
            square_length -= 1
    x = len(square) - 1
    y = len(square) - 1
    if len(message) ** 0.5 != len(square):
        for num in range((len(square) ** 2) - len(message) % (len(square) ** 2)):
            square[x][y] = ' '
            y -= 1
            if y < 0:
                y = len(square) - 1
                x -= 1
    x = 0
    y = 0
    l = 0
    while l < len(message):
        if square[x][y] != ' ':
            square[x][y] = message[l]
            l += 1
        x += 1
        if x >= len(square):
            x = 0
            y += 1
    result = []
    for column in square:
        result.append(''.join(column))
    result = list(''.join(result))
    return ''.join( i for i in result if i.isalpha())
