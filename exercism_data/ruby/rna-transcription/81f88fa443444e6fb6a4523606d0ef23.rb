class Complement

  def self.of_dna(strand)
    conversions = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
    self.find(strand, conversions)
  end

  def self.of_rna(strand)
    conversions = { 'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A'}
    self.find(strand, conversions)
  end

  def self.find(strand, conversions)
    complement = []
    letters = strand.split('')
    letters.each { |letter| complement << conversions[letter] }
    complement.join
  end

end
