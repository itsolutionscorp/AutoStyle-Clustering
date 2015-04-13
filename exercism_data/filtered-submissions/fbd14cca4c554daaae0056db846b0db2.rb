def compute(a, b)
    return -1 unless a.length == b.length
    a.split('').each_with_index.collect{|c, indx| c == b[indx]}.count(false)
  end