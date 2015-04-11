class Complement
  COMPLEMENTS = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}

  def self.of_dna dna
    dna.chars.map { |c| COMPLEMENTS[c] }.join
  end

  def self.of_rna rna
    rna.chars.map { |c| COMPLEMENTS.key(c) }.join
  end
end
