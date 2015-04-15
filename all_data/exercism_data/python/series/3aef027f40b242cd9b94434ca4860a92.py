#
#
#

def slices(num_string,n):
    if (n > len(num_string)) or n == 0:
        raise ValueError('Length error')

    num_list = [int(char) for char in num_string]
    out_list = []

    for i in range(0,(len(num_list) - n + 1)):
        window = num_list[i:i+n]
        out_list.append(window)

        """ USE THIS FOR CASE WHEN NUMBERS MUST BE NUMERICALLY CONSECUTIVE
        n_max = max(window)
        n_min = min(window) - 1
        expected_sum = 0.5*(n_max*(n_max+1) - n_min*(n_min+1))
        print window, expected_sum, n_max,n_min
        if sum(window) == expected_sum:
            out_list.append(window)
        """

    return out_list
