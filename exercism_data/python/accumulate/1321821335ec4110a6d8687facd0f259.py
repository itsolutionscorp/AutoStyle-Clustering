def accumulate(arr, block):
    return [block(element) for element in arr]
