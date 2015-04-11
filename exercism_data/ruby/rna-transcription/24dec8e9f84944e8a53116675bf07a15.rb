class Complement
  DNA_COMPLEMENT = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  RNA_COMPLEMENT = DNA_COMPLEMENT.invert

  def self.of_dna strand
    strand.chars.map { |nucleotide| DNA_COMPLEMENT[nucleotide] }.join
  end

  def self.of_rna strand
    strand.chars.map { |nucleotide| RNA_COMPLEMENT[nucleotide] }.join
  end
end
