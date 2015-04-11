def detect_anagrams(source, text_array):
    return [ word for word in text_array if (len(word) == len(source) and (source.lower() != word.lower()) and (sorted(source.lower()) == sorted(word.lower()))) ]
