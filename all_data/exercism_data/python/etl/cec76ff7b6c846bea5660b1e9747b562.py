def transform(legacy_system):
    # New dictionary to store the result
    new_system = dict()

    # Go through the list of keys in the legacy system (i.e. the scores)
    for score in legacy_system:
        # Go through the list of keys in the new system (i.e. the letters themselves)
        for key in legacy_system[score]:
            # Store the elements on the new system
            # The letter is now the key, and the score is now the value
            new_system[key.lower()] = score

    return new_system
