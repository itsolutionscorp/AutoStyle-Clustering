def compute(strand1, strand2)
    counter = 0
     (0...[strand1.length, strand2.length].min).count do |i|
      counter += (strand2[i].nil? || strand1[i] == strand2[i]) ? 0 : 1
    end
    counter
  end