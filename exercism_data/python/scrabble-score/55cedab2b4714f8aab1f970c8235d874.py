value = {1: "AEIOULNRST", 2: "DG", 3: "BCMP",
         4: "FHVWY", 5: "K", 8: "JX", 10: "QZ"}

def score(st):
    result = 0

    for c in st.upper():
        for k, v in value.items():
            if c in v:
                result += k
                continue

    return result
