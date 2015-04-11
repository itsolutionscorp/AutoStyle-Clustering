def sieve(n):

    sieve_range = range(2, n+1)
    elements = [[i, 'unmarked'] for i in sieve_range]

    for i, element in enumerate(elements):
        value, state = element
        if state == 'unmarked':

            rest = elements[(i+1):]
            for item in rest:
                if item[0] % value == 0:
                    item[1] = 'marked'

    return [value for value,state in elements
                if state == 'unmarked']
