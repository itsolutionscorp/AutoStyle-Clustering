#William Morris
#exercism.io
#series.py

def multiples(s_list):
    multiple = 1
    for num in s_list:
        multiple *= num
    return [multiple]

def slices(series,n):
    num_slices = len(series)-n +1
    if n<=0 or num_slices <=0:
        raise ValueError("length 'n' not valid")
    int_slice_list = []
    for i in range(num_slices):
        string_slice = series[i:i+n]
        int_slice = [int(num) for num in string_slice]
        int_slice_list += [int_slice]
    return int_slice_list
     
def largest_product(series, n):
    if n==0 and series=='':
        return 1        
    mult_list = []
    for s in slices(series,n):
        mult_list += multiples(s)
    return max(mult_list)

    
