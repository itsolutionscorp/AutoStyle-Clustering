class Complement
  def self.of_dna(strand)
    complement(strand, "dna")
  end

  def self.of_rna(strand)
    complement(strand, "rna")
  end

  def self.complement(strand, strand_type)
    strand = strand.chars
    nucleic_mapping = generate_map(strand_type)

    strand.inject('') do |nucleotide_complement, nucleotide|
      raise ArgumentError if !nucleic_mapping.keys.include?(nucleotide)
      nucleotide_complement << nucleic_mapping[nucleotide]
    end
  end

  def self.generate_map(nucleic_type)
    map = { "G" => "C", "C" => "G", "T" => "A", "A" => "U" }

    if nucleic_type == "rna"
      map = map.invert
    end

    map
  end
end
