class Complement
  
  @output = {
    'C' => 'G',
    'G' => 'C',
    'T' => 'A',
    'A' => 'U',
  }
  
  def self.of_dna(letter)
    map = @output
    letter.chars.map{|i| map[i]}.join
  end
  
  def self.of_rna(letter)
    map = @output.invert
    letter.chars.map{|i| map[i]}.join
  end
end  
