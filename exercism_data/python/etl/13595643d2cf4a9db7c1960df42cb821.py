def transform( dic ):
    return { key.lower():val for val in dic for key in dic[val] }
