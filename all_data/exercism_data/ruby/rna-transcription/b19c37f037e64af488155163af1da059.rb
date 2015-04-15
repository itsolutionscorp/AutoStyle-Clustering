class Complement

  def self.dna_complement(dna_nucl)
    { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }[dna_nucl]
  end

  def self.rna_complement(rna_nucl)
    { 'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A' }[rna_nucl]
  end

  def self.of_dna(dna)
    dna.chars.map { |c| dna_complement(c) }.join
  end

  def self.of_rna(rna)
    rna.chars.map { |c| rna_complement(c) }.join
  end

end


# p Complement.of_dna('G')
