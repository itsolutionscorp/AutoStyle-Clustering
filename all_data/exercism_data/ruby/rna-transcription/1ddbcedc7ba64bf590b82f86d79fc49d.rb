class Complement
  TO_RNA = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  TO_DNA = {'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A'}
  def self.of_dna(dna)
    dna.split('').inject('') {|result, i| result + TO_RNA[i] }
  end
  def self.of_rna(dna)
    dna.split('').inject('') {|result, i| result + TO_DNA[i]}
  end
end
