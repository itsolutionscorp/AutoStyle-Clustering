def compute(strand_1, strand_2)
    [strand_1, strand_2].map(&:size).min.times.count do |i|
      strand_1[i] != strand_2[i]
    end
  end