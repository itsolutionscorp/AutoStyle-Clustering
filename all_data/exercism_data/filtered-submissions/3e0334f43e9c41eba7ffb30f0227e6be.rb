def compute(s1, s2)
    hamming = []
    s1.length.times{|i| hamming << 1 unless s1[i] == s2[i]}
   	hamming.count
  end