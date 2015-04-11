class Complement
  @map = { 
    'C' => 'G',
    'G' => 'C',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(string)
    replace_with_map(string, @map)
  end

  def self.of_rna(string)
    replace_with_map(string, @map.invert)
  end

  def self.replace_with_map(string, map)
    output = []
    string.each_char do |char|
      output << map.fetch(char, char)
    end
    output.join
  end
end
