class Complement
  DNA = {'C' => 'G','G' => 'C', 'T' => 'A', 'A' => 'U'}
  RNA = DNA.invert

  def self.of_dna(dna_strand)
    dna_strand.each_char.map { |nucleotide| DNA[nucleotide] }.join
  end

  def self.of_rna(rna_strand)
    rna_strand.each_char.map { |nucleotide| RNA[nucleotide] }.join
  end
end
