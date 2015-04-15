def transform(old):
    return {entry.lower(): score 
            for score, entries in old.items()
            for entry in entries}
