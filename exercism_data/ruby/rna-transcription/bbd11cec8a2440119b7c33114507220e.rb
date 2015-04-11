module Complement

  DNA_TO_RNA_TABLE = {
    'A' => 'U',
    'C' => 'G',
    'G' => 'C',
    'T' => 'A'
  }

  RNA_TO_DNA_TABLE = {
    'U' => 'A',
    'G' => 'C',
    'C' => 'G',
    'A' => 'T'
  }

  def self.of_dna(dna_strand)
    rna_strand = ''
    dna_strand.split(//).each { |base| rna_strand << DNA_TO_RNA_TABLE[base] }
    rna_strand
  end

  def self.of_rna(rna_strand)
    dna_strand = ''
    rna_strand.split(//).each { |base| dna_strand << RNA_TO_DNA_TABLE[base] }
    dna_strand
  end

end
