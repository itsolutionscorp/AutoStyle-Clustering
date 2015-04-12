def compute (strand1, strand2)
    hamming_distance = 0
    index = 0

    while strand1[index] && strand2[index]
      hamming_distance += 1 if strand1[index] != strand2[index]
      index += 1
    end

    return hamming_distance

  end
end