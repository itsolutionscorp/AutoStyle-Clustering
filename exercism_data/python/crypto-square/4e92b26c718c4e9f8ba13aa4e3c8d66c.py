from math import ceil, sqrt

def encode(text):

    text_list = [i for i in text.lower() if i.isalnum()]
    square_size = int(ceil(sqrt(len(text_list))))
    cipher_list = [text_list[i::square_size] for i in xrange(square_size)]
    
    return ' '.join([''.join(x) for x in cipher_list])
