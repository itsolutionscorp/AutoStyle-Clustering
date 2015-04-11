def parse_binary(str_bin):
    #checking if there are other values except 01
    if ord(max(str_bin)) > 49 or ord(min(str_bin)) < 48:
        raise ValueError
    return(sum([2**cnt for cnt,i in enumerate(reversed(str_bin)) if i == '1']))
