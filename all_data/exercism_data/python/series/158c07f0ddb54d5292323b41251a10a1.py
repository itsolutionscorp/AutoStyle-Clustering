def slices(number_string, series_length):
    
    slices_array = []
    i = 0
    
    if (series_length <= len(number_string)) and series_length != 0:

        while (len(number_string) - i) >= series_length:

            new_slice = number_string[i:i+series_length]
            new_slice_array = []

            for j in range(0, len(new_slice)):

                new_slice_array.append(int(new_slice[j]))

            slices_array.append(new_slice_array)
            i += 1

        return slices_array

    else:
        raise ValueError
