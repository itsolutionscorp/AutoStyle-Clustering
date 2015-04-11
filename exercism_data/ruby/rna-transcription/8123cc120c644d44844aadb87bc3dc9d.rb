class Complement
  @@dna_to_rna = {
    'A' => 'U',
    'G' => 'C',
    'C' => 'G',
    'T' => 'A'
  }

  @@rna_to_dna = {
    'U' => 'A',
    'A' => 'T',
    'G' => 'C',
    'C' => 'G'
  }

  def self.of_dna(input)
    input.split('').map{|i| @@dna_to_rna[i]}.reduce(:+)
  end

  def self.of_rna(input)
    input.split('').map{|i| @@rna_to_dna[i]}.reduce(:+)
  end
end
