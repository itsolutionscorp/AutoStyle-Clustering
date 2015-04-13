def compute(strand_one, strand_two)


    [strand_one.size, strand_two.size].min.times.count do |i|
      strand_one[i] != strand_two[i]
    end
  end