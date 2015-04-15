class Complement
  DNA_TO_RNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(dna)
    dna.chars.map { |c| DNA_TO_RNA[c] }.join
  end

  def self.of_rna(rna)
    rna.chars.map { |c| RNA_TO_DNA[c] }.join
  end
end
