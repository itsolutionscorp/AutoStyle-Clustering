def to_rna(n):
    """
    Transcribe DNA to RNA.
        `G` -> `C`
        `C` -> `G`
        `T` -> `A`
        `A` -> `U`
    """
    result = ''
    transcription = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    for i in n:
        if i.upper() in transcription:
            result += transcription[i]

    return result
