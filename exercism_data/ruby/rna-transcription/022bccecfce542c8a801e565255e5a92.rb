class Complement
  DNA_TO_RNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_rna str
    rna_to_dna = DNA_TO_RNA.invert
    str.split('').map{|letter| rna_to_dna[letter]}.join
  end

  def self.of_dna str
    str.split('').map{|letter| DNA_TO_RNA[letter]}.join
  end
end
