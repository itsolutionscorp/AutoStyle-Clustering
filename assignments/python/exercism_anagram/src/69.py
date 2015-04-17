import types
def detect_anagrams(ana, phrase):
    output = []
    if len(phrase) == 1:
        output = []
    else:
        for words in phrase:  # phrase.split()
            lettersum = 0
            if len(words) == len(ana) and ana.lower() != words.lower():
                for letters in words:
                    if letters.lower() in ana.lower():
                        lettersum += 1
                if lettersum == len(words):
                    output.append(words)
    return output
