class Complement
  def self.of_dna(dna_strand)
    dna_strand.split("").map{|dna_nuc| map_dna_nuc_to_rna_nuc(dna_nuc)}.join("")
  end

  def self.of_rna(rna_strand)
    rna_strand.split("").map{|rna_nuc| map_rna_nuc_to_dna_nuc(rna_nuc)}.join("")
  end

  def self.dna_mappings
    {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }
  end

  def self.rna_mappings
    dna_mappings.invert
  end

  def self.map_dna_nuc_to_rna_nuc(dna_nuc)
    dna_mappings[dna_nuc]
  end

  def self.map_rna_nuc_to_dna_nuc(rna_nuc)
    rna_mappings[rna_nuc]
  end
end
