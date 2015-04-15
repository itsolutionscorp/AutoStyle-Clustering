def transform(old_dict):
    return {item.lower(): key
            for key, value in old_dict.iteritems()
            for item in value}
