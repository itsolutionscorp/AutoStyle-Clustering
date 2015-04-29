def compute(strand1, strand2)
    hamming_difference = 0
    strand1.chars.zip(strand2.chars).each do |a1,a2|
      break if a1.nil? or a2.nil?
      hamming_difference += 1 unless a1 == a2
    end
    hamming_difference
  end