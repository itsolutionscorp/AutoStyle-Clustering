def compute(strand1, strand2)
    hamming_distance = 0
    index = 0

    if strand1.size > strand2.size
      length = strand2.size
    else
      length = strand1.size
    end

    while index < length do
      hamming_distance += 1 if strand1[index] != strand2[index]
      index += 1
    end
    return hamming_distance