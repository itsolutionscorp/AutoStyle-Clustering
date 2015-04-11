class Complement
  def self.of_dna(strand)
    complement(strand, dna_map)
  end

  def self.of_rna(strand)
    complement(strand, rna_map)
  end

  # private class methods below

  def self.complement(strand, nucleic_mapping)
    strand = strand.chars

    strand.inject('') do |nucleotide_complement, nucleotide|
      raise ArgumentError if !nucleic_mapping.keys.include?(nucleotide)
      nucleotide_complement << nucleic_mapping[nucleotide]
    end
  end

  def self.dna_map
    { "G" => "C", "C" => "G", "T" => "A", "A" => "U" }
  end

  def self.rna_map
    dna_map.invert
  end

  private_class_method :complement, :dna_map, :rna_map
end
