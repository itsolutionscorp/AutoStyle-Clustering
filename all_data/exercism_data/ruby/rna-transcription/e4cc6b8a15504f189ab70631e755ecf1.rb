class Complement
  DNA_TO_RNA = {'G' => 'C',
                'C' => 'G',
                'T' => 'A',
                'A' => 'U'}

  def self.of_dna(dna)
    apply_to_all_nucleotides(dna) {|x| dna_nucleotide_complement(x)}
  end

  def self.of_rna(rna)
    apply_to_all_nucleotides(rna) {|x| rna_nucleotide_complement(x)}
  end

  private
  def self.apply_to_all_nucleotides(strand, &block)
    strand.chars.map{|x| block.call(x)}.join
  end

  def self.dna_nucleotide_complement(nucleotide)
    DNA_TO_RNA[nucleotide]
  end

  def self.rna_nucleotide_complement(nucleotide)
    DNA_TO_RNA.invert[nucleotide]
  end
end
