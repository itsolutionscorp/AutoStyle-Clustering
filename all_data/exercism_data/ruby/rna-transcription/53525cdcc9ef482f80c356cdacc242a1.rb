class Complement
  DNA_STRAND_COMPLEMENTS = { 'A' => 'U', 'T' => 'A', 'G' => 'C', 'C' => 'G' }
  
  def self.of_dna dna_strand
    map_complements dna_strand, DNA_STRAND_COMPLEMENTS
  end

  def self.of_rna rna_strand
    map_complements rna_strand, DNA_STRAND_COMPLEMENTS.invert
  end

  def self.map_complements strand, complement_array
    strand.chars.map do |nucleotide|
      raise ArgumentError unless complement_array[nucleotide]
      complement_array[nucleotide]
    end.join
  end
end
