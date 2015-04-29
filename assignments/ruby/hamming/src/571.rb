def compute(strand_1, strand_2)
    hamming_distance = 0
    count_length= [strand_1.length, strand_2.length].min
    (0...count_length).each do |i|
      hamming_distance += 1 unless strand_1[i] == strand_2[i]
    end
    hamming_distance
  end