class Complement
  RNA_TO_DNA = { 'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A' }
  DNA_TO_RNA = { 'C' => 'G', 'G' => 'C', 'A' => 'U', 'T' => 'A' }

  def self.of_dna(dna)
    dna.split('').map { |letter| DNA_TO_RNA[letter] }.join
  end

  def self.of_rna(rna)
    rna.split('').map { |letter| RNA_TO_DNA[letter] }.join
  end
end
