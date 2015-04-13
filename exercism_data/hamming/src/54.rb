def compute(strand1, strand2)
    hamming_distance = 0
    compare_length = [strand1.length, strand2.length].min
    (0...compare_length).each do |i|
      hamming_distance += 1 if strand1[i] != strand2[i]
    end
    hamming_distance
  end