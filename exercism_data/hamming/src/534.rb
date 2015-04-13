def compute(s1, s2)
    hamming = 0
    string_length = s1.length - 1
    if s1 == s2
      return hamming
    else
      (0..string_length).to_a.each do |n|
        hamming += 1 if s1[n] != s2[n]
      end
    hamming
    end
  end