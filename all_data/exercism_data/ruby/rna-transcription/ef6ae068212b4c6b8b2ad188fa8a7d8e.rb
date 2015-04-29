class Complement
  DNA_MAPPING = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  RNA_MAPPING = DNA_MAPPING.invert

  def self.of_dna(strand)
    strand.gsub(/./, DNA_MAPPING)
  end

  def self.of_rna(strand)
    strand.gsub(/./, RNA_MAPPING)
  end
end
