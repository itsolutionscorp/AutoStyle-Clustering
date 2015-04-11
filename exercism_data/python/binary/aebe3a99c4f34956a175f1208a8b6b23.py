__author__ = 'tracyrohlin'

def parse_binary(bin_string):
    try:
        int(bin_string, 2)
        bin_num = [int(n) for n in bin_string]
        result = 0
        exp = len(bin_num)-1
        for n in bin_num:
            result += n*(2**exp)
            exp -= 1
        return result




    except:
        raise ValueError()
