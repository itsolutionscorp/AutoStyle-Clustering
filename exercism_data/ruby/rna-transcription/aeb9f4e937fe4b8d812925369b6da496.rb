class Complement
  DNA_TO_RNA_MAPPING = {
    'C' => 'G',
    'G' => 'C',
    'T' => 'A',
    'A' => 'U',
  }.freeze

  def self.of_dna(dna)
    invert(dna, :fetch)
  end

  def self.of_rna(rna)
    invert(rna, :key)
  end

  def self.invert(strand, method)
    strand.chars.map { |nucleotide|
      DNA_TO_RNA_MAPPING.send(method, nucleotide)
    }.join
  end
end
