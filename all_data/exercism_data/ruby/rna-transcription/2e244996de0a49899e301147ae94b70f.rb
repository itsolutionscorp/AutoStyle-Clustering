class Complement

  @@dna_to_rna = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}

  def self.of_dna chain
    chain.split('').map{|letter| @@dna_to_rna[letter]}.join
  end

  def self.of_rna chain
    chain.split('').map{|letter| @@dna_to_rna.key(letter)}.join
  end

end
