class Complement
  TO_RNA = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  TO_DNA = {'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A'}
  def self.of_dna(dna)
    result = ''
    dna.each_char {|i| result += TO_RNA[i] }
    return result
  end
  def self.of_rna(dna)
    result = ''
    dna.each_char {|i| result+= TO_DNA[i]}
    return result
  end
end
