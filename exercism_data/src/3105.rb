def compute(strand1, strand2)
    differences = 0
    strand1.length.times do |i|
      differences += 1 unless strand1[i] == strand2[i]
    end
    differences
  end