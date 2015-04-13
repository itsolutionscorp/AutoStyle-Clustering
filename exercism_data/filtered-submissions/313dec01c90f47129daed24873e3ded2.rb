def compute(strand1, strand2)
    distance = 0

    if strand1 == strand2
      return distance
    end

    max = strand1.length > strand2.length ? strand2.length : strand1.length

    for i in 0..max - 1

      distance += strand1[i] == strand2[i] ? 0 : 1;
    end
    return distance
  end