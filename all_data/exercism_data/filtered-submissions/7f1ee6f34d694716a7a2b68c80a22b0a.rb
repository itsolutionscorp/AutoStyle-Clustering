def compute (strand1, strand2)


    dist = 0

    (0..strand1.length).each do |i|
    dist += 1 unless strand1[i] == strand2[i]
    end

    return dist
  end