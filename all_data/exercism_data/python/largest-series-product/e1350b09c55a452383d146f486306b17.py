import operator

def product(xs):
	return reduce(operator.mul, xs)

def slices(digits_string, length):
        if length > len(digits_string) or length == 0:
                raise(ValueError)
        digits = map(int, digits_string)
        slice_starts = range(len(digits)-length + 1)
        return [digits[start:start+length] for start in slice_starts]

def largest_product(xs, length):
	return max(map(product, slices(xs, length))) if xs else 1
