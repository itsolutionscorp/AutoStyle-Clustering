def compute(strand_one, strand_two)
    [strand_one.length, strand_two.length].min.times.count do |i|
      strand_one[i] != strand_two[i]
    end
  end