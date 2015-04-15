class Complement
  def self.of_dna(strand)
    self.complement(strand, "dna")
  end

  def self.of_rna(strand)
    self.complement(strand, "rna")
  end

  def self.complement(strand, strand_type)
    strand = strand.split('')
    complement = ''
    nucleic_mapping = generate_map(strand_type)

    strand.each do |nucleotide|
      raise ArgumentError if !nucleic_mapping.keys.include?(nucleotide)
      complement << nucleic_mapping[nucleotide]
    end

    complement
  end

  def self.generate_map(type)
    map = { "G" => "C", "C" => "G", "T" => "A", "A" => "U" }

    if type == "rna"
      map = map.invert
    end

    map
  end
end
