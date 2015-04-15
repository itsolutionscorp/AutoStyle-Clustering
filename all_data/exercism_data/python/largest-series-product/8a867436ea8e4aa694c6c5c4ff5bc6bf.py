series1 ="52677741234314237566414902593461595376319419139427"              


def slices(series, slice_length):
    if not 1 <= slice_length <= len(series):
        raise ValueError(
            "Slice length, {}, is less than 1 or greater than series length,{}".format(
                slice_length, len(series)))

    series = [int(s) for s in series]
    return [series[i:i + slice_length]
            for i in range(len(series) + 1 - slice_length )]


def _product(numbers):
    result = 1
    for number in numbers:
        result *= number
    return result


def largest_product(series, slice_length):
    if slice_length == 0:
        return 1
    return max(_product (s) for s in slices(series,slice_length))
               
               
#print(max(_product (s) for s in slices(series1,6)))               
#print(largest_product(series1, 6))               
