def compute original, *descendant
    raise ArgumentError('Sequences must be equal in length.') if original.length != descendant.length

    [*original].zip(descendant).reduce(0) { |a, (orig_base, desc_base)|
      a += 1 if orig_base != desc_base
      a
    }
  end