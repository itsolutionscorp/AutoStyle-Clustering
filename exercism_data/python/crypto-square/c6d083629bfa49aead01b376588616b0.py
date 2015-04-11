from math import sqrt

def encode(to_encode):
    to_encode = normalise_text(to_encode)
    size = len(to_encode)
    if size == 0:
        return ''
    while not perfect_square(size):
        size += 1

    step = int(sqrt(size))

    my_list = list(filter(None,[to_encode[x: x + step]
                                for x in range(0, size + 1, step)]))

    my_list = pad_out_list(my_list)
    my_list = [''.join([item[x] for item in my_list])
               for x in range(len(my_list[0]))]
    
    return ' '.join(my_list)

def normalise_text(string):
    #return a-z and 0-9
    string = str(string)
    return [x for x in string.lower()
            if 96 < ord(x) < 123
            or 47 < ord(x) <58]

def perfect_square(n):
    return n % n**0.5 == 0

def pad_out_list(list_to_pad):
    for index, item in enumerate(list_to_pad):
        if len(item)< len(list_to_pad[0]):
            list_to_pad[index].extend([''] * (len(list_to_pad[0]) - len(list_to_pad[index])))
    return list_to_pad
