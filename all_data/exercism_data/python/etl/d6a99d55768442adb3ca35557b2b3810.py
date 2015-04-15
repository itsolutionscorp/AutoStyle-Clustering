def transform(m):
    return {v.lower(): k for k, vs in m.iteritems() for v in vs}
