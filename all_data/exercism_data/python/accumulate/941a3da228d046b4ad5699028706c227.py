def accumulate(numbers, function):
    result = []
    for n in numbers:
        result.append(function(n))
    return result;
