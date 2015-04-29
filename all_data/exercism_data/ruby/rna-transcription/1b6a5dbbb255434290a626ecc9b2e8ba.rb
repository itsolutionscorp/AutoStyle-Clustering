class Complement
  DNA_TO_RNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(dna)
    dna.chars.map { |nuc| DNA_TO_RNA[nuc] }.join
  end

  def self.of_rna(rna)
    rna.chars.map { |nuc| RNA_TO_DNA[nuc] }.join
  end
end
