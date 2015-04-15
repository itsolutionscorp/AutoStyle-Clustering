def transform(old_dict):
    return {word.lower():score
            for score in old_dict
            for word in old_dict[score]}
