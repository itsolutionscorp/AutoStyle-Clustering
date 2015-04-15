class Hamming

  def self.compute(strand1,strand2)
    return if strand1.length != strand2.length

    diff = 0
    i = 0

    while (i < strand1.length)
      diff += 1 if (strand1[i] != strand2[i])

      i += 1
    end

    return diff
  end
end
