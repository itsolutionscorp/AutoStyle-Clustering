def slices(series, length):
    if len(series) < length or length < 1:
        raise ValueError
    answer = []
    while True:
        answer.append([int(number) for number in series[:length]])
        series = series[1:]
        if len(series) < length:
            break
    print answer
    return answer
