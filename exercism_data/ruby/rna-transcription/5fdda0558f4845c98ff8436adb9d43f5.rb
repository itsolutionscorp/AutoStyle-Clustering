class Complement
  DNA_TO_RNA = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }

  def self.of_dna(strand)
    strand.chars.map { |nucleotide| DNA_TO_RNA[nucleotide] }.join
  end

  def self.of_rna(strand)
    strand.chars.map { |nucleotide| DNA_TO_RNA.key(nucleotide) }.join
  end
end
