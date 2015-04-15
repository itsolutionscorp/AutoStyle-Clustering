def transform(old_dict):
    return {word.lower():score
            for score, words in old_dict.items()     
            for word in words}
