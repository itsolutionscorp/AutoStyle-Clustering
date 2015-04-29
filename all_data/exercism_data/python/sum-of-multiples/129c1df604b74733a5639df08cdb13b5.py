def sum_of_multiples(n, multiples=[3, 5]):
    multiples = validate_multiples(multiples)
    return sum(x for x in range(n) if is_multiple(x, multiples))


def validate_multiples(multiples):
    if 0 in multiples:
        multiples.remove(0)
    return multiples


def is_multiple(n, multiples):
    for mult in multiples:
        if n % mult == 0:
            return True
    return False
