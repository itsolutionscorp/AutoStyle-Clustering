def compute (strand1, strand2)
    range = [strand1.length, strand2.length].min.times

    range.count do |i|
      strand1[i] != strand2[i]
    end
  end