class Complement
  @@dna_to_rna = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}

  def self.of_dna(strand)
    complement(strand,@@dna_to_rna)
  end

  def self.of_rna(strand)
    complement(strand,@@dna_to_rna.invert)
  end

  def self.complement(strand, lookup)
    comp_strand = ''
    strand.each_char do |nucleotide|
      comp_strand += lookup[nucleotide]
    end
    comp_strand
  end
end
