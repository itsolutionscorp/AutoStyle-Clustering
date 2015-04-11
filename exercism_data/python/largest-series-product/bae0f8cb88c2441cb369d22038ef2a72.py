def slices(num_str, n):
    digits = len(num_str)
    if n > digits:
        raise ValueError('n <= len(your_number)')
    result = []
    for i in range(digits - n + 1):
        result.append([int(j) for j in num_str[i:i+n]])
    return result

def largest_product(num_str, n):
    products = []
    for element in slices(num_str, n):
        product = 1
        for elem in element:
            product *= elem
        products.append(product)
    return max(products)
