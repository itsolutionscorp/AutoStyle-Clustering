def sieve(limit):
    all_nums = list(range(2, limit))

    for num in all_nums:
        multiples = [num * x for x in range(2, int(limit/num) + 1)]
        all_nums = [number for number in all_nums if number not in multiples]

    return all_nums

if __name__ == '__main__':
    print(sieve(10))
