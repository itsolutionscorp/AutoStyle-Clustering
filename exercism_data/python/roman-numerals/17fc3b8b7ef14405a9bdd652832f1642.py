__author__ = 'jeffmarkey'

def numeral(decimal_number):
    conversion = {}
    new_decimal_number = 0

    counts = [ 0,   0,   0,   0,   0,   0,   0 ]
    max_val =[ 4,   1,   3,   1,   3,   1,   3 ]
    values = ['M', 'D', 'C', 'L', 'X', 'V', 'I']

    # Ideal Counts
    counts[0], new_decimal_number = count_numerals(decimal_number, 1000)
    counts[1], new_decimal_number = count_numerals(new_decimal_number, 500)
    counts[2], new_decimal_number = count_numerals(new_decimal_number, 100)
    counts[3], new_decimal_number = count_numerals(new_decimal_number, 50)
    counts[4], new_decimal_number = count_numerals(new_decimal_number, 10)
    counts[5], new_decimal_number = count_numerals(new_decimal_number, 5)
    counts[6], new_decimal_number = count_numerals(new_decimal_number, 1)

    return write_final_value(counts, values, max_val)


def preprocess_value(counts, max_val):
    new_counts = []
    for index in len(counts):
        if(counts[index] > max_val[index]):
            counts[index] = counts[index]


def write_final_value(counts, values, max_values):
    return_value = ''

    index = 0
    for count, value, max_val in zip(reversed(counts), reversed(values), reversed(max_values)):
        if(count > max_val):
            # Swapping characters
            values[len(values)-(index+2)],values[len(values)-(index+1)] = values[len(values)-(index+1)],values[len(values)-(index+2)]

            # Swapping counts
            counts[len(values)-(index+2)] += 1
            counts[len(values)-(index+1)] -= max_val

            if(counts[len(values)-(index+2)] > max_values[len(values)-(index+2)]):
                counts[len(values)-(index+2)] -= max_values[len(values)-(index+2)]
                values[len(values)-(index+3)],values[len(values)-(index+1)] = values[len(values)-(index+1)],values[len(values)-(index+3)]
                # counts[len(values)-(index+2)] = 0

        index = index + 1

    index = 0
    for count, value, max_val in zip(reversed(counts), reversed(values), reversed(max_values)):
        if(count > max_val):
            # Swapping characters
            values[len(values)-(index+2)],values[len(values)-(index+1)] = values[len(values)-(index+1)],values[len(values)-(index+2)]

            # Swapping counts
            counts[len(values)-(index+2)] += 1
            counts[len(values)-(index+1)] -= max_val

            if(counts[len(values)-(index+2)] > max_values[len(values)-(index+2)]):
                counts[len(values)-(index+2)] -= max_values[len(values)-(index+2)]
                values[len(values)-(index+3)],values[len(values)-(index+1)] = values[len(values)-(index+1)],values[len(values)-(index+3)]
                # counts[len(values)-(index+2)] = 0

        index = index + 1


    for count, value, max_val in zip(counts, values, max_values):
        for element in range(count):
            return_value = return_value + value

    return return_value

def count_numerals(decimal_number, roman_value):
    val = 0
    new_decimal_value = decimal_number
    while( decimal_number > 0 ):
        decimal_number -= roman_value
        if(decimal_number >= 0):
            val = val+1
            new_decimal_value = decimal_number
        else:
            new_decimal_value = decimal_number+roman_value
    return val, new_decimal_value
