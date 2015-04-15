module Complement
  DNA_TO_RNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  RNA_TO_DNA = {
    'C' => 'G',
    'G' => 'C',
    'A' => 'T',
    'U' => 'A'
  }
  
  def self.of_dna dna_strand
    rna = ''
    dna_strand.each_char {|tide| rna << DNA_TO_RNA[tide]}
    rna
  end
  
  def self.of_rna rna_strand
    dna = ''
    rna_strand.each_char {|tide| dna << RNA_TO_DNA[tide]}
    dna
  end
end
