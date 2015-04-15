def slices(string, n):
    all_series = []
    if n > len(string) or n < 1:
      raise ValueError

    for i in range(len(string)-n+1):
      series = [int(x) for x in list(string[i:i+n])]
      all_series.append(series)
    return all_series

def multiply(slices):
    product = 1
    for i in slices:
      product = product * i
    return product

def largest_product(string, n):
    all_series = slices(string, n)
    products = []
    for series in all_series:
      products.append(multiply(series))
    return max(products)
