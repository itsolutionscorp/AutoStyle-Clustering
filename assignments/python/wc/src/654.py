def word_count(blob):
    '''Simply using a python dictionary, after splitting the input
    into space separated tokens...test for key existence'''
    dict = {}
    for tk in blob.split():
        dict[tk] = 1 if tk not in dict else dict[tk] + 1
    return dict
