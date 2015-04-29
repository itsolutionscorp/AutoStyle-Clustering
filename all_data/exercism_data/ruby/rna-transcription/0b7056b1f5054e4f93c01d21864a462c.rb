class Complement

  DNA_TO_RNA = {
    'C' => 'G',
    'T' => 'A',
    'A' => 'U',
    'G' => 'C'
  }

  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(dna)
    translate_strand(dna, DNA_TO_RNA)
  end

  def self.of_rna(rna)
    translate_strand(rna, RNA_TO_DNA)
  end

  private

  def self.translate_strand(strand, mapping)
    strand.each_char.map { |nucleotide| mapping[nucleotide] }.join
  end

end
