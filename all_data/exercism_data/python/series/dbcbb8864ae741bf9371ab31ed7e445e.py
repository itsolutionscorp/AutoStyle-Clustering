#William Morris
#exercism.io
#series.py

def slices(series,n):
    """
Takes a string of digits and returns a list of lists of digits with all the
possible consecutive number series of length 'n' in that string.
i.e. the string '01234' has the following 3-digit series:
[ [0,1,2],[1,2,3],[2,3,4] ]
"""
    num_slices = len(series)-n +1
    if n<=0 or num_slices <=0:
        raise ValueError("length 'n' not valid")
    int_slice_list = []
    for i in range(num_slices):
        string_slice = series[i:i+n]
        int_slice = [int(num) for num in string_slice]
        int_slice_list += [int_slice]
    return int_slice_list
     
