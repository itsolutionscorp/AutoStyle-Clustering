def transform(scores):
    return {item.lower(): key for key, val in scores.items() for item in val}
