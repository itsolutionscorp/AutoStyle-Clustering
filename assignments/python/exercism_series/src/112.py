def slices(numbers, nth):
    if nth < 1 or nth > len(numbers):
        raise ValueError()
    return [list(map(int, numbers[i:i + nth])) for i in
            range(0, len(numbers) - (nth - 1))]
