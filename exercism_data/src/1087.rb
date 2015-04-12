def compute strand1, strand2
    return "Size mismatch" if (strand1.size != strand2.size)

    diff = 0
    strand1.size.times do |position|
      diff += 1 unless strand1[position] == strand2[position]
    end

    return diff
  end