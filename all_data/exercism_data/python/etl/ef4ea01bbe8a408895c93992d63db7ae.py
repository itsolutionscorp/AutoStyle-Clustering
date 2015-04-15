
def transform(old):
    return {item.lower(): value
            for value, list in old.items()
            for item in list}
