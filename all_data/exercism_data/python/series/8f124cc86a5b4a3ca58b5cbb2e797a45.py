# Series

def slices(number_string, length):
    if length > len(number_string) or length < 1:
        raise ValueError("Make sure the length fits the series!")
        
    else:
        result = []
        for i in range(0, len(number_string) - length + 1):
            result.append(list(map(int, number_string[i:i + length])))
        return result
