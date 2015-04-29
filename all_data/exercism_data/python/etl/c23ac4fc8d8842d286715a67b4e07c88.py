def transform(old_data):
    new_data = {}
    for points, values in old_data.iteritems():
        new_data.update({v.lower(): points for v in values})
    return new_data
