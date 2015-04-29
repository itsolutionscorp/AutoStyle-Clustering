def transform(dc):
    return {i.lower():key for key in dc.keys() for i in dc[key]}
