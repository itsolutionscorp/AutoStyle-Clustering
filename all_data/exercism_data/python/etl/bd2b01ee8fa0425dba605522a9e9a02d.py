def transform(old_dict):
    """Transforms a dictionary of (score, list) or (score, string)
    elements into a dictionary of (item in list:score) or
    (character in string:score)
    """
    new_dict = {}
    for score in old_dict:
        for item in list(old_dict[score]):
                new_dict[item.lower()] = score
    return new_dict
