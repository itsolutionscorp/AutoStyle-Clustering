"""Implements the rules in the README.md.
"""
def slices(number_string, series_length):
    """Prints all consecutive series from numbers based on series_length.
    """
    """Test that we aren't passing garbage."""
    if not (series_length >= 1 and len(number_string) >= series_length):
        raise ValueError("Invalid parameters passed")
    """The tests are looking for lists, not strings so we must convert."""
    number_list = [int(i) for i in number_string]
    """The code below returns a slice for each valid position based on the
    requested series length vs the available digits in the list/string."""
    return [number_list[i:i + series_length] # create slice
        for i in range(len(number_list) - series_length + 1)]
