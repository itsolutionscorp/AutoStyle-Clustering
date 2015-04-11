__author__ = 'tracyrohlin'

def sum_of_multiples(n, multiples=None):
    new_sum = 0
    if multiples:
        for item in multiples:
            for i in range(n):
                if i % item == 0:
                    new_sum += i
        return new_sum
    else:
        for i in range(n):
            if i % 3 == 0:
                new_sum += i

            elif i % 5 == 0:
                new_sum += i
        return new_sum

print sum_of_multiples(20, [7, 13, 17])
