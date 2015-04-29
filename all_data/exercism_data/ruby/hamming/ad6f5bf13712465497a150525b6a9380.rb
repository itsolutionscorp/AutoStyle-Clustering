class Hamming
  def self.compute(dna1, dna2)
    return unless dna1 && dna2 && dna1.length == dna2.length

    (dna1.chars).zip(dna2.chars).count {|char1, char2|
      char1 != char2
    }
  end
end
