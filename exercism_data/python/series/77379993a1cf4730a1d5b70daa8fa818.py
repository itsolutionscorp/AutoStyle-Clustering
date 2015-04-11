def slices(numbers, length):
    if length == 0 or length > len(numbers):
        raise ValueError
    numbers = [int(number) for number in numbers]
    return [numbers[i:i+length] for i in range(len(numbers)-length+1)]
