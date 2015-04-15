def slices(string, size):
    if not size or size > len(string):
        raise ValueError

    numbers = [int(x) for x in list(string)]
    return [numbers[start:start + size] for start in
            range(len(numbers) - size + 1)]
