def detect_anagrams(prime, candidates):
    results = []
    for word in candidates:
        if word.lower() != prime.lower():
            if sorted(word.lower()) == sorted(prime.lower()):
                results.append(word)
    return results
