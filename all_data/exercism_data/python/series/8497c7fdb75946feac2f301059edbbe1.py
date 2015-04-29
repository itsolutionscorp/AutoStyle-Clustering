def slices(string, substring_length):
    if substring_length > len(string):
        raise ValueError("You can't have a slice longer than the string.")

    # Cast string to a list of numbers
    digit_list = list(string)
    digit_list = [int(digit) for digit in digit_list]

    substring_list = []

    for i in range(len(digit_list) - substring_length + 1):
        # This part is correct in that it passes the tests,
        # but now I don't understand why it works.
        # It seems like a substring length of 1 would return
        # substrings of length 2.
        substring_list.append(digit_list[i:i + substring_length])

    return substring_list


def largest_product(string, substring_length):
    list_of_slices = slices(string, substring_length)
    list_of_products = [product_of_list(number_list) for number_list in list_of_slices]
    return max(list_of_products)


def product_of_list(list_of_numbers):
    product = 1
    for number in list_of_numbers:
        product *= number
    return product
