def compute(strand1, strand2)
    strand1_a = strand1.split("")
    strand2_a = strand2.split("")
    counter = 0
    i = 0

    while strand1_a.size > strand2_a.size
      strand1_a.pop
    end

    while strand2_a.size > strand1_a.size
      strand2_a.pop
    end

    strand1_a.each do |s1|
      if s1 != strand2_a[i]
        counter += 1
      end
      i += 1
    end

    return counter

  end