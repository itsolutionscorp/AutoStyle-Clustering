def slices(string, step):
    if not step or step > len(string):
        raise ValueError('Invalid Step Size.')
    return [map(int, string[i:i+step]) for i in range(len(string)-step+1)]
