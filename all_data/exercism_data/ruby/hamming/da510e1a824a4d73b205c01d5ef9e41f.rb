class Hamming
  def self.compute(strand1, strand2)
    hamming_distance(strand1, strand2)
  end

  class << self

  private

    def hamming_distance(strand1, strand2)
      strands = strand1.chars.zip(strand2.chars)
      strands.count do |(n1, n2)|
        n1 != n2 if n1 && n2
      end
    end
  end
end
