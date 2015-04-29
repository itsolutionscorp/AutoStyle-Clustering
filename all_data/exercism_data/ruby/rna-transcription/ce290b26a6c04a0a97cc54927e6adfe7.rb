class Complement
  def self.of_dna(dna_strand)
    nucleotide_mapping = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
    nucleotide_translator(dna_strand, nucleotide_mapping)
  end
  def self.of_rna(rna_strand)
    nucleotide_mapping = { 'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A' }
    nucleotide_translator(rna_strand, nucleotide_mapping)
  end
  private def self.nucleotide_translator(strand, mapping)
      strand.each_char.map { |nucleotide| mapping[nucleotide] }.join
  end
end
