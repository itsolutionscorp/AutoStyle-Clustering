class Hamming

  def self.initialize(strand1, strand2)
    @strand1 = strand1
    @strand2 = strand2
  end

  def self.compute(strand1, strand2)
    num, diff = 0, 0
    verify_array(strand1, strand2)
    a = strand1.split('')
    b = strand2.split('')
    while num < a.length
      if a[num] != b[num]
        diff += 1
      end
      num += 1
    end
    return diff
  end

  def self.verify_array(strand1, strand2)
    return if strand1.length == strand2.length

    if strand1.length < strand2.length
      return strand2.slice!(-1)
    elsif strand1.length > strand2.length
      return strand1.slice!(-1)
    end
  end

end
