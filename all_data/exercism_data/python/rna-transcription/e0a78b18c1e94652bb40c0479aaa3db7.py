def to_rna(strand):
    """Given a DNA strand, returns its RNA complement (per RNA transcription)"""
    # is that a dna encoded that was given to me ?
    if any( {c not in "GTAC" for c in strand} ):
        # found something else than C,G,A,T. Something is horribly wrong !
        # or, there was a bug I need to signal.
        raise ValueError("the strand given to transcribe is not valid !")

    complements = {"G":"C","C":"G","T":"A","A":"U"}
    # comprehension, join into a string and return.
    return "".join( complements[nucleotid] for nucleotid in strand )
