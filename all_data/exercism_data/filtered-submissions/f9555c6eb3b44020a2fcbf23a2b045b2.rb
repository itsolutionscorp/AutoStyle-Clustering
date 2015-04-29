def compute(strand1, strand2)


    count = strand1.length


    strand1 = strand1.chars.to_a
    strand2 = strand2.chars.to_a

    differences = 0
    length = 0


    until length == count
      if strand1[length] != strand2[length]
       differences += 1
      end
      length += 1
    end

    return differences

  end