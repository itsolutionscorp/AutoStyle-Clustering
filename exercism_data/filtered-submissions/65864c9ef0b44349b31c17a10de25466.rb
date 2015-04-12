def compute(strand1, strand2)
    hamming_dist = 0
    strand1.chars.each_index do |i|
      hamming_dist += 1 if strand1[i] != strand2[i]
    end
    hamming_dist
  end