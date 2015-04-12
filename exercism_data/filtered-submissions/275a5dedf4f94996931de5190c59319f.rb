def compute(a, b)
    hamming_distance = 0
    (0..[a,b].min_by{|x| x.length}.length-1).each {|i| hamming_distance += 1 if a[i]!=b[i]}
    return hamming_distance
  end