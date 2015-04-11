class Complement

  def self.of_dna(strand)
    strand.gsub(nucleotide, DNA_TO_RNA )
  end

  def self.of_rna(strand)
    strand.gsub(nucleotide, rna_to_dna )
  end

  private

  DNA_TO_RNA = {
    'C' => 'G',
    'G' => 'C',
    'T' => 'A',
    'A' => 'U'
  }

  def self.nucleotide 
    /./
  end

  def self.rna_to_dna
    DNA_TO_RNA.invert 
  end

end
