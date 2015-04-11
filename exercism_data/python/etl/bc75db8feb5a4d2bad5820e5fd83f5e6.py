def transform(old_map):
    list_of_dicts = (dict.fromkeys([s.lower() for s in v], k) for k, v in old_map.iteritems())
    return reduce(lambda d, src: d.update(src) or d, list_of_dicts, {})
