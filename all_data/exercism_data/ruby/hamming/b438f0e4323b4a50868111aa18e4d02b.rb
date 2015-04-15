class Hamming
  def self.compute(strand1, strand2)
    if strand1.length != strand2.length
      return -1
    end

    distance = 0
    strand1.length.times do |i|
      if (!isValidBase(strand1[i]) || !isValidBase(strand2[i]))
        return -2
      end

      if (strand1[i] != strand2[i])
        distance += 1
      end
    end
    return distance
  end

  def self.isValidBase(base)
    base == 'A' || base == 'C' || base == 'G' || base == 'T'
  end
end
