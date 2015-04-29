from collections import Counter
from typing import Dict


def word_count(text: str) -> Dict[str, int]:
    counts = Counter()  # type: Dict[str, int]
    for word in text.split():
        word = word.strip()
        counts[word] += 1

    return counts
