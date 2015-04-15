class Hamming
  def self.compute(strand1, strand2)
    length = length_for_compute(strand1, strand2)
    strands = strand1[0, length].chars.zip(strand2[0, length].chars)
    strands.count do |(n1, n2)|
      mutation?(n1, n2)
    end
  end

  class << self

  private

    def mutation?(n1, n2)
      n1 != n2
    end

    def length_for_compute(s1, s2)
      s1.length <= s2.length ? s1.length : s2.length
    end
  end
end
