class Complement

  DNA_TO_RNA = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}

  def self.of_dna chain
    chain.split('').map{|letter| DNA_TO_RNA[letter]}.join
  end

  def self.of_rna chain
    chain.split('').map{|letter| DNA_TO_RNA.key(letter)}.join
  end


end
