def compute(s1, s2)
    hamming = 0
    (0..s1.length).step(1) {|i| hamming += 1 unless s1[i] == s2[i] }
    hamming
  end