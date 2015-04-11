module Complement

  extend self

DNA_TO_RNA_COMPLIMENTS = {
  'G' => 'C',
  'C' => 'G',
  'T' => 'A',
  'A' => 'U',
}

  def of_dna(dna)
    complements = []
    dna = dna.split(//)
    dna.each do |nucleotide|
      complements << DNA_TO_RNA_COMPLIMENTS[nucleotide]
    end

    complements.join('')
  end

  def of_rna(rna)
    complements = []
    rna = rna.split(//)
    rna.each do |nucleotide|
      complements << DNA_TO_RNA_COMPLIMENTS.key(nucleotide)
    end

    complements.join('')
  end
end
