def compute (strand1, strand2)
    range = (0...[strand1.length, strand2.length].min)

    range.count do |i|
      strand1[i] != strand2[i]
    end
  end