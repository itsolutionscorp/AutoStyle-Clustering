def compute(strand_1, strand_2)
    distance = 0
    length = [strand_1.size, strand_2.size].min
    (0..length - 1).each do |i|
      distance += 1 unless strand_1[i] == strand_2[i]
    end
    distance
  end