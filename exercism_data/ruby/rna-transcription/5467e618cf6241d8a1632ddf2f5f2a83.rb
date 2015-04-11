class Complement

  DNA_TO_RNA = {
    'C' => 'G',
    'T' => 'A',
    'A' => 'U',
    'G' => 'C'
  }

  RNA_TO_DNA = {
    'C' => 'G',
    'U' => 'A',
    'A' => 'T',
    'G' => 'C',
  }

  def self.of_dna(dna)
    translate_strand(dna, DNA_TO_RNA)
  end

  def self.of_rna(rna)
    translate_strand(rna, RNA_TO_DNA)
  end

  private

  def self.translate_strand(strand, mapping)
    strand.chars.map { |nucleotide| mapping[nucleotide] }.join
  end

end
