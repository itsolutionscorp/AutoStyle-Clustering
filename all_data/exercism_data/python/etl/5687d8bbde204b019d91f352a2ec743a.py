def transform(old_dict):
    return{element.lower(): k for k, v in old_dict.items() 
                            for element in v}
