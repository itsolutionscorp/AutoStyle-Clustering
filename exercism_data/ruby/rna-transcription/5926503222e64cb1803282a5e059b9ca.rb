class Complement
  def self.of_dna(strand)
    self.complement(strand, "dna")
  end

  def self.of_rna(strand)
    self.complement(strand, "rna")
  end

  def self.complement(strand, strand_type)
    strand = strand.chars
    nucleic_mapping = generate_map(strand_type)

    strand.inject('') do |memo, nucleotide|
      raise ArgumentError if !nucleic_mapping.keys.include?(nucleotide)
      memo << nucleic_mapping[nucleotide]
    end
  end

  def self.generate_map(type)
    map = { "G" => "C", "C" => "G", "T" => "A", "A" => "U" }

    if type == "rna"
      map = map.invert
    end

    map
  end
end
