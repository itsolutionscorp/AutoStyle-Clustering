class Complement
  DNA_TO_RNA_MAPPING = {
    'C' => 'G',
    'G' => 'C',
    'T' => 'A',
    'A' => 'U',
  }.freeze

  def self.of_dna(dna)
    dna.chars.inject('') { |rna_complement, nucleotide|
      rna_complement << DNA_TO_RNA_MAPPING.fetch(nucleotide)
    }
  end

  def self.of_rna(rna)
    rna.chars.inject('') { |dna_complement, nucleotide|
      dna_complement << DNA_TO_RNA_MAPPING.key(nucleotide)
    }
  end
end
