def compute(strand1, strand2)

    min_length = [strand1.length, strand2.length].min

    min_length.times.count do |i|
      strand1[i] != strand2[i]
    end
  end