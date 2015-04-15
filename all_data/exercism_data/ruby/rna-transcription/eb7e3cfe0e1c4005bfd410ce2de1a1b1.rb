class Complement
  COMPLEMENT_OF_RNA = {
      'G' => 'C',
      'C' => 'G',
      'A' => 'T',
      'U' => 'A'
  }
  COMPLEMENT_OF_DNA = COMPLEMENT_OF_RNA.invert

  def self.of_dna(dna_strand)
    strand_complement(dna_strand, COMPLEMENT_OF_DNA)
  end

  def self.of_rna(rna_strand)
    strand_complement(rna_strand, COMPLEMENT_OF_RNA)
  end

  private
  def self.strand_complement(strand, mapping)
    # Element-wise mapping of original strand to its complement
    # using the appropriate constant
    complement_array = strand.chars.map { |nucleotide| mapping[nucleotide] }
    complement_array.join
  end
end
