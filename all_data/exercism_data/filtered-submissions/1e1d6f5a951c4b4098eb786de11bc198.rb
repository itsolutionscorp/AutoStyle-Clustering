def compute(strand1, strand2)
    hamming_distance = 0
    [strand1, strand2].map(&:length).sort.first.times do |i|
      hamming_distance += 1 if strand1[i] != strand2[i]
    end
    hamming_distance
  end