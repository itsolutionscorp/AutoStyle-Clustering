def transform(dictionnary):
    """transforms a dictionnary according to exercism "etl".

    input dict is in the form {int:string}
    output dict will be in the form {string:int}"""

    out = dict()
    for key,iterable in dictionnary.items():
        for i in iterable:
            out[i.lower()] = key
    return out
