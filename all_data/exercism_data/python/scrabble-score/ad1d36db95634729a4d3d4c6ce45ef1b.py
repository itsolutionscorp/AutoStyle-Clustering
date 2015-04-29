VALUES = {}
for ks, v in [
            ('AEIOULNRST', 1),
            ('DG', 2),
            ('BCMP', 3),
            ('FHVWY', 4),
            ('K', 5),
            ('JX', 8),
            ('QZ', 10),
        ]:
    VALUES.update(dict.fromkeys(ks, v))

def score(text):
    return sum(VALUES.get(ch, 0) for ch in text.upper())
