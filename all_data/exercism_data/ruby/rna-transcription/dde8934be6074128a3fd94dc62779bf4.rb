class Complement
  DNA_MAPPING = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  RNA_MAPPING = DNA_MAPPING.invert

  def self.of_dna(nucleotides)
    map_nucleotide_chain_using(DNA_MAPPING, nucleotides)
  end

  def self.of_rna(nucleotides)
    map_nucleotide_chain_using(RNA_MAPPING, nucleotides)
  end

  private

  def self.map_nucleotide_chain_using(mapping, nucleotides)
    nucleotides.chars.map { |nucleotide|
      mapping[nucleotide]
    }.join
  end

end
