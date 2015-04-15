from typing import Dict


def word_count(text: str) -> Dict[str, int]:
    counts = {}
    for word in text.split():
        word = word.strip()

        if word not in counts:
            counts[word] = 1
        else:
            counts[word] += 1

    return counts
