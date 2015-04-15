def transform(old_dict):
    
    expected={}
    for k, v in old_dict.items():
        for element in v:
            expected[element.lower()] = k
    
    return expected
