def compute(strand1, strand2)
    # Get the length of the smaller strand
    min_length = [strand1.length, strand2.length].min
    # Counting up the distance every time the two strands do not match
    min_length.times.count do |i|
      strand1[i] != strand2[i]
    end
  end