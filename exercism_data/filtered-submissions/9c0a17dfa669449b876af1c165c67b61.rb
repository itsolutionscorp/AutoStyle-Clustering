def compute(strand1, strand2)
    size = [strand1.size, strand2.size].min

    size.times.count { |index|
      strand1[index] != strand2[index]
    }
  end