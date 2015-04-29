def detect_anagrams(selection, candidates):
    return filter(
        lambda x: (
            sorted(list(x.lower())) == sorted(list(selection.lower()))
                and
            x.lower() != selection.lower()
        ), candidates
    )
