class Complement
  DNA_TO_RNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(dna)
    dna.each_char.map { |c| DNA_TO_RNA[c] }.join
  end

  def self.of_rna(rna)
    rna.each_char.map { |c| RNA_TO_DNA[c] }.join
  end
end
