def sieve(limit):
    limit += 1
    numbers = range(2, limit)
    marked = [True, True] + limit * [False]

    while(False in marked):
        index = marked.index(False)
        revised_numbers = numbers
        marked[index] = True

        for number in numbers:
            if not marked[number] and number % index == 0:
                revised_numbers.remove(number)
                marked[number] = True

        numbers = revised_numbers

    return numbers
