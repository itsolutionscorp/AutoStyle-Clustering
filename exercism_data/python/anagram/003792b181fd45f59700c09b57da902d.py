def detect_anagrams(target, words):
    anagrams = []

    for word in words:
        lword = word.lower()
        ltarget = target.lower()

        if lword != ltarget and len(word) == len(target):
            word_chars = sorted(list(set(lword)))
            target_chars = sorted(list(set(ltarget)))

            word_counts = [lword.count(c) for c in word_chars]
            target_counts = [ltarget.count(c) for c in target_chars]

            if word_chars == target_chars and word_counts == target_counts:
                anagrams.append(word)

    return anagrams
