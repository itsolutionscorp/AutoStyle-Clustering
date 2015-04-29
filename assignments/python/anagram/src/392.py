def detect_anagrams(target, candidates):
    sorted_candidates = ["".join(sorted(awords.lower())) for awords in candidates]
    sorted_target = "".join(sorted(target.lower()))
    sort_can_dict = dict(zip(candidates, sorted_candidates))
    sorted_matched = []
    matched = []
    for astring in sorted_candidates:
        if astring == sorted_target:
            sorted_matched = astring
            break
    for x in sort_can_dict:
            if sorted_matched == sort_can_dict[x] and x.lower() != target.lower():
                matched.append(x)
    matched.reverse()
    return matched
