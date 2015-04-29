def slices(numbers, length):
    if length > len(numbers):
        raise ValueError("Slice longer than number string.")
    else:
        result = []
        for i in range(0,len(numbers)-(length-1)):
            result.append([int(x) for x in numbers[i:i+length]])
        return result
    
def largest_product(numbers, length):
    if length > len(numbers):
        raise ValueError("Slice longer than number string.")
    else:
        results = []
        for segment in slices(numbers, length):
            total = 1
            for item in segment:
                total = total * item
            results.append(total)
        return max(results)
