"""Extract-Transform-Load."""


def transform(dic):
    """Transform {k: [..., v, ...]} into {v.lower(): k}."""
    return {v.lower(): k for k, vs in dic.iteritems() for v in vs}
