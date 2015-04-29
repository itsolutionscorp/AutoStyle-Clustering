class Complement
  DNA_MAPPINGS = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  RNA_MAPPINGS = DNA_MAPPINGS.invert

  def self.of_dna(strand)
    map_strand(strand, DNA_MAPPINGS)
  end

  def self.of_rna(strand)
    map_strand(strand, RNA_MAPPINGS)
  end

  def self.map_strand(strand, mappings)
    strand.tr(mappings.keys.join(""), mappings.values.join(""))
  end
end
