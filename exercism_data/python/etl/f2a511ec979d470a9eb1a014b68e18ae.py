def transform(old_data):
    return {scrabble_word.lower(): score for score in old_data for scrabble_word in old_data[score]}
