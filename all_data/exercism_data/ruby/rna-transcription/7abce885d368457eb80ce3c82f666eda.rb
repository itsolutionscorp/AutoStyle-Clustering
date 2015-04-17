module Complement
  DNA_TO_RNA_MAP = {
    'A' => 'U',
    'C' => 'G',
    'G' => 'C',
    'T' => 'A'
  }

  RNA_TO_DNA_MAP = DNA_TO_RNA_MAP.invert

  def self.of_dna(dna_strand)
    dna_strand.chars.inject('') { |rna_strand, base| rna_strand << DNA_TO_RNA_MAP[base] }
  end

  def self.of_rna(rna_strand)
    rna_strand.chars.inject('') { |dna_strand, base| dna_strand << RNA_TO_DNA_MAP[base] }
  end
end