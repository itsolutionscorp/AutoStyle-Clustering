def sum_of_multiples(limit: int, multiples=(3, 5)) -> int:
    multiples = tuple(m for m in multiples if m)
    total = 0
    for number in range(1, limit):
        for multiple in multiples:
            if not number % multiple:
                total += number
                break
    return total
