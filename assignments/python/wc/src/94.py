from collections import Counter
import re
def word_count(phrase):
    return Counter(re.sub(r'\W+', ' ', phrase).lower().split())
