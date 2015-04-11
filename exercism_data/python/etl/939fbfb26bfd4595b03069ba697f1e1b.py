def transform(old_dict):
    return {element.lower():key
            for key, value in old_dict.items()
            for element in value}
