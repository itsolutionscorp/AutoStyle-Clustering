def compute(strand1, strand2)
    return 0 if strand1 == strand2
    length = [ strand1.length, strand2.length ].min
    (0..length-1).map { |i| strand1[i] == strand2[i] }.count(false)
  end