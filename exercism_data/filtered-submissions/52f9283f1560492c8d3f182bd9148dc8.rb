def compute strand_1, strand_2
    [strand_1.length,strand_2.length].min.times.count do |n|
      strand_1[n] != strand_2[n]
    end
  end