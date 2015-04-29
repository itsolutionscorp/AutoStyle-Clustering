def accumulate(items, fn):
    "Map a list of items into a list transformed by a function"
    transformed_items = []
    for item in items:
        transformed_items.append(fn(item))
    return transformed_items
