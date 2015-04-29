# Etl

def transform(old_values):
    result = {}

    for score in old_values:
        for string in old_values[score]:
            result[string.lower()] = score
            
    return result
