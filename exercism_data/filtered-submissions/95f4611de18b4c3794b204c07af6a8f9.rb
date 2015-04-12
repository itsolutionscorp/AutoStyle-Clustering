def compute(base, compare)
    raise ArgumentError if base.length != compare.length
    
    (0..(base.length-1)).reduce(0) { |diff, index| base[index] != compare[index] ? diff + 1: diff }
  end