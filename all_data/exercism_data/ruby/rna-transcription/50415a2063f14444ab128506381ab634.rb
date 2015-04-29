class Complement

  def self.pairs
    { 
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }
  end

  def self.of_dna(dna)
    dna.chars.map { |c| pairs[c.upcase] }.join
  end

  def self.of_rna(rna)
    rna.chars.map { |c|  pairs.key(c.upcase) }.join
  end
end
