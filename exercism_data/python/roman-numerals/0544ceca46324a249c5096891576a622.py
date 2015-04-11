# List of equivalences based on the digit's value
# I'm assuming that (per the instructions) numbers won't be greater than 3,000
numerals = {'4':['IV','XL','CD'],'9':['IX','XC','CM'],'<4':['I','X','C','M'],'>5':['V','L','D']}

# Return the Roman numeral representation of the given number
def numeral(arabic):
    # Validate the input
    if not isinstance(arabic, (int,long)):
        return ''

    result = []

    # Go through the list of digits in reverse order, starting with ones
    # Get the appropriate numeral for each digit
    # Store each result at the beginning of the list, so that the result is automatically reversed
    for index, digit in enumerate(str(arabic)[::-1]):
        result.insert(0,convert_any(digit,index))

    return ''.join(result)

# Convert any digit in the number using the list of numerals
def convert_any(digit,index):

    # Roman numerals don't support 0 as such, skip it
    if digit == '0':
        return ''

    # Convert the digit to int for comparing its value
    digit = int(digit)

    # Figure out the appropriate element in the numerals table
    if digit < 5:
        return numerals['4'][index] if digit == 4 \
            else numerals['<4'][index] * digit
    else:
        return numerals['9'][index] if digit == 9 \
            else numerals['>5'][index] + numerals['<4'][index] * (digit - 5)
