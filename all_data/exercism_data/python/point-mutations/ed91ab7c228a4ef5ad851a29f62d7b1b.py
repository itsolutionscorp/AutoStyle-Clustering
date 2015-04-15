class DNA(object):
  def __init__(self, source):
    self._source = source

  def hamming_distance(self, sequence):
    return len([n for i, n
                  in enumerate(sequence[0:len(self._source)])
                  if n != self._source[i]])
