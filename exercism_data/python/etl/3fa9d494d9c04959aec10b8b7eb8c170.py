def transform(old):
    return { let.lower(): val for val, lets in old.items() for let in lets }
