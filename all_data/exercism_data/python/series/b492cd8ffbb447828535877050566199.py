 
def slices(numbers, slice_length):
    count = 0
    return_array = []
    if slice_length != 0 and slice_length <= len(numbers):
        while count <= len(numbers):
            values = []
            for number in numbers[count:count+slice_length]:
                values.append(int(number))
            if len(values) == slice_length:
                return_array.append(values)
            else:
                break
            count += 1
    else:
        raise ValueError("the given count of numbers is less than the slice length")
    return return_array
