def compute(s1, s2)
    hamming_count = 0
    s1_a = s1.split(//)
    s2_a = s2.split(//)

    s1_a.each_with_index do |item, index|
      if item != s2_a[index]
        hamming_count = hamming_count+1
      end
    end
    hamming_count
    
  end