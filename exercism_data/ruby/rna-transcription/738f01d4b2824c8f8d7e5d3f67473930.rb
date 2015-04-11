class Complement
  DNA_TO_RNA= { 'G' => 'C',
                'C' => 'G',
                'T' => 'A',
                'A' => 'U' }

  def self.of_rna(strand)
    remap_strand(strand) { |nucleotide| rna_complement(nucleotide) } 
  end

  def self.of_dna(strand)
    remap_strand(strand) { |nucleotide| dna_complement(nucleotide) } 
  end

  def self.remap_strand(strand, &block)
    strand.chars.map do |nucleotide|
      yield nucleotide
    end.join ''
  end

  def self.dna_complement(nucleotide)
    DNA_TO_RNA[nucleotide]
  end

  def self.rna_complement(nucleotide)
    DNA_TO_RNA.invert[nucleotide]
  end
end
