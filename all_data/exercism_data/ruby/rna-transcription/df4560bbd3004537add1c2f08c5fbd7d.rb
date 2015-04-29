class Complement

  DNA_TO_RNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(dna_nucleotides)
    translation(dna_nucleotides, DNA_TO_RNA)
  end

  def self.of_rna(rna_nucleotides)
    translation(rna_nucleotides, RNA_TO_DNA)
  end

  def self.translation(nucleotides, criterion)
    nucleotides.each_char.map do |nucleotide|
      criterion[nucleotide]
    end.join
  end

end
