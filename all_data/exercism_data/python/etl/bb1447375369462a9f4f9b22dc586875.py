def transform(old):
    return {string.lower(): key for key, stringlist in old.iteritems() for string in stringlist}
