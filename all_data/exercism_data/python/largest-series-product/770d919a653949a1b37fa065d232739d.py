#
#
#


def largest_product(num_string,n):
    # Calculate largest product for series of n consecutive digits
    if (n > len(num_string)):
        raise ValueError('Length error')
    if n == 0:
        # Identity
        return 1

    all_slices = slices(num_string,n)
    all_products = [ reduce(lambda x,y: x*y, s) for s in all_slices]
    return max(all_products)

def slices(num_string,n):
    if (n > len(num_string)) or n <= 0:
        raise ValueError('Length error')

    num_list = [int(char) for char in num_string]
    out_list = []

    for i in range(0,(len(num_list) - n + 1)):
        window = num_list[i:i+n]
        out_list.append(window)

    return out_list
