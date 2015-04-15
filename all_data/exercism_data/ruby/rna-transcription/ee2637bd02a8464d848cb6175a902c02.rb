class Complement

  DNA_TO_RNA = { 'T' => 'A', 'A' => 'U', 'C' => 'G', 'G' => 'C' }
  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(dna)
    return dna.each_char.collect { |nucleotide| DNA_TO_RNA[nucleotide] }.join
  end

  def self.of_rna(rna)
    return rna.each_char.collect { |nucleotide| RNA_TO_DNA[nucleotide] }.join
  end

end
