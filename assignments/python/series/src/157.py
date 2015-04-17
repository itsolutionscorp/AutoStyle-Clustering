def slices(string, n):
    numbers = [int(digit) for digit in string]
    if not 1 <= n <= len(numbers):
        raise ValueError("Invalid series length: " + str(n) + " asked for a: " + str(len(string)) + " digits string!")
    return [numbers[i:i + n] for i in range(len(numbers) - n + 1)]
