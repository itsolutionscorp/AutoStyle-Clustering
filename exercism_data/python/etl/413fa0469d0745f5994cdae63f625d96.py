def transform(legacy_system):
    new_system = dict()
    for score in legacy_system:
        for key in legacy_system[score]:
            new_system[key.lower()] = score
    return new_system
