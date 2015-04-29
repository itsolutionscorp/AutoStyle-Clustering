class Complement
  def self.of_dna(dna_strand)
    dna = Strand.new(dna_strand)
    dna.convert_strand_to_rna(dna_strand)
  end

  def self.of_rna(rna_strand)
    rna = Strand.new(rna_strand)
    rna.convert_strand_to_dna(rna_strand)
  end
end

class Strand
attr_accessor :strand
RNA_TO_DNA = {
  'C' => 'G',
  'A' => 'T',
  'U' => 'A',
  'G' => 'C'
}

DNA_TO_RNA = {
 'G' => 'C',
 'C' => 'G',
 'T' => 'A',
 'A' => 'U'
}
  def initialize(strand)
    @strand = strand.split(//)
  end

  def convert_strand_to_rna(dna_strand)
    @strand.map { |char| DNA_TO_RNA.fetch(char)  }.reduce(:+)
  end

  def convert_strand_to_dna(rna_strand)
    @strand.map { |char| RNA_TO_DNA.fetch(char)  }.reduce(:+)
  end

end
