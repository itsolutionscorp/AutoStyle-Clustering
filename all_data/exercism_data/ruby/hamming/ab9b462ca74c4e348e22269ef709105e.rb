class Hamming
  def self.compute(strand1, strand2)
    strands = strand1.chars.zip(strand2.chars)
    strands.count do |(n1, n2)|
      mutation?(n1, n2)
    end
  end

  class << self

  private

    def mutation?(n1, n2)
      n1 != n2 if n1 && n2
    end
  end
end
