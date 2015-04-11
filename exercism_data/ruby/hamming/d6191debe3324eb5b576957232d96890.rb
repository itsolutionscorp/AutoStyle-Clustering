class Hamming
  def self.compute(dna, another_dna)
    dna.chars.map.with_index do |char, index|
      char != another_dna[index] ? 1 : 0
    end.reduce(:+)
  end
end
