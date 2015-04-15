def sum_of_multiples(limit, numbers=[3,5]):
    multiples = set()
    for number in numbers:
        if number:
            for multiple in range(number, limit, number):
                multiples.add(multiple)
    return sum(multiples)
