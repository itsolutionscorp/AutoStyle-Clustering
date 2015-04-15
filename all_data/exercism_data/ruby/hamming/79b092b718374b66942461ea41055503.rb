class Hamming
  def self.compute(dna1, dna2)
    return unless dna1 && dna2 && dna1.length == dna2.length

    (dna1.chars).zip(dna2.chars).inject(0) {|different_pairs, (char1, char2)|
      (char1 == char2) ? different_pairs : different_pairs + 1
    }
  end
end
