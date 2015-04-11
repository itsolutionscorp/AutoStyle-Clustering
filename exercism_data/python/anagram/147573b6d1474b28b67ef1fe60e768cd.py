'''exer anagram'''


def detect_anagrams(root, candidates):
    '''return those members of candidates that anagrams of root'''
    rslts = []
    root = root.lower()
    sorted_root = sort_string(root)

    for candidate in candidates:
        lower_candidate = candidate.lower()
        if lower_candidate == root:
            continue                            # same word, so skip it

        if sorted_root == sort_string(lower_candidate):
            rslts.append(candidate)             # same letters so append

    return rslts

def sort_string(buf):
    '''return string sorted'''
    return ''.join(sorted(list(buf)))
