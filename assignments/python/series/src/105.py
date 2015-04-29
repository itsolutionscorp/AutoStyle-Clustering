__author__ = 'grdunn'
def slices(slab, chunk):
    """
    :type chunk: int
    :param slab:
    :param chunk:
    """
    the_slices = []
    for i in slab:
        the_slices.append([int(j) for j in slab[int(i):int(i)+chunk]])
    return the_slices
