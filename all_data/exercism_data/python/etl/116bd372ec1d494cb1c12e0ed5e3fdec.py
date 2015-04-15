__author__ = 'Momo'


def transform(old_data : dict):
    assert type(old_data) == dict, "Input is not a dictionary."
    return {str.lower(v) : k for k in old_data for v in old_data.get(k)}
