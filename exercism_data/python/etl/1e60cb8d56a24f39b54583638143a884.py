__author__ = 'agupt15'


def transform(old):
    if old is None:
        return None
    
    return {str.lower(element): k for k, v in old.items() for element in v}
