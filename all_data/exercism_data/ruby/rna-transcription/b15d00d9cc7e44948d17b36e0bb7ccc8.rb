class Complement
  def self.of_dna(strand)
    complement(strand, "dna")
  end

  def self.of_rna(strand)
    complement(strand, "rna")
  end

  private

  def self.complement(strand, strand_type)
    strand = strand.chars
    nucleic_mapping = generate_map(strand_type)

    strand.inject('') do |nucleotide_complement, nucleotide|
      raise ArgumentError if !nucleic_mapping.keys.include?(nucleotide)
      nucleotide_complement << nucleic_mapping[nucleotide]
    end
  end

  def self.generate_map(strand_type)
    return dna_map if strand_type == "dna"
    rna_map
  end

  def self.dna_map
    { "G" => "C", "C" => "G", "T" => "A", "A" => "U" }
  end

  def self.rna_map
    dna_map.invert
  end
end
