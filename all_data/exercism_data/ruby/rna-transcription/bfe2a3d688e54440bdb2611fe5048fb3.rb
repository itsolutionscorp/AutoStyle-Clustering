class Complement
  @@to_rna_hash = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  @@to_dna_hash = @@to_rna_hash.invert

  def self.of_dna(dna)
    dna.chars.map{|c| @@to_rna_hash[c]}.join
  end

  def self.of_rna(dna)
    dna.chars.map{|c| @@to_dna_hash[c]}.join
  end
end
