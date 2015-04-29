module Complement
  DNA_TO_RNA_TABLE = {
    'A' => 'U',
    'C' => 'G',
    'G' => 'C',
    'T' => 'A'
  }

  RNA_TO_DNA_TABLE = DNA_TO_RNA_TABLE.invert

  def self.of_dna(dna_strand)
    dna_strand.chars.inject('') { |rna_strand, base| rna_strand << DNA_TO_RNA_TABLE[base] }
  end

  def self.of_rna(rna_strand)
    rna_strand.chars.inject('') { |dna_strand, base| dna_strand << RNA_TO_DNA_TABLE[base] }
  end
end
