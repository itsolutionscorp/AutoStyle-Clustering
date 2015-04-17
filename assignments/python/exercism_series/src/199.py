def slices(st, n):
    if n == 0 or n > len(st):
        raise ValueError('index out of range')
    slice_list = []
    b,e = 0,n
    while e <= len(st):
        slice_list.append([int(s) for s in st[b:e]])
        b += 1
        e += 1
    return slice_list
if __name__ == '__main__':
    print(slices('1234', 3))
