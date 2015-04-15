def slices(number,length):
    if length <= len(number):
        sliced = []
        number_as_list = []
        for char in number:
            number_as_list.append(int(char))
        for i in range(0,len(number)-length+1):
            sliced.append(number_as_list[i:i+length])
        return sliced
    else:
        raise ValueError

def largest_product(number,length):
    sliced = slices(number,length)
    all_products = []
    for a_slice in sliced:
        product = 1
        for n in a_slice:
            product = product*n
        all_products.append(product)
    return max(all_products)
