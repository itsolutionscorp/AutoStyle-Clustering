class Complement
  
  RNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  
  DNA = {
    'C' => 'G',
    'G' => 'C',
    'A' => 'T',
    'U' => 'A'
  }
  
  def self.of_dna(strand)
    strand.chars.map {|char| RNA[char]}.join("")
  end
  
  def self.of_rna(strand)
    strand.chars.map {|char| DNA[char]}.join("")
  end
  
end
