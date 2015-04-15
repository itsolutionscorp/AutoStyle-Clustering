class Hamming
  def self.compute(dna_one, dna_two)
    dna_one.chars.zip(dna_two.chars).count do |dna_one_char, dna_two_char|
       dna_one_char && dna_two_char && dna_one_char != dna_two_char
    end
  end
end
