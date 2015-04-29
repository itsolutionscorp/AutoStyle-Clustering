def detect_anagrams(base, potentials):
    """Returns a list of anagrams for the base from a list of potentials"""

    # Eliminate any duplicates from potentials
    potentials = [p for p in potentials if p.lower() != base.lower()]

    # Split base and potential into lists of lower-case characters, sort them, then test if equal
    return [p for p in potentials if sorted(p.lower()) == sorted(base.lower()) ]
