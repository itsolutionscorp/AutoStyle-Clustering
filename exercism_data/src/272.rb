def compute(strand1, strand2)
    (0...[strand1.size, strand2.size].min).count do |i|
      strand1[i] != strand2[i]
    end
  end
end