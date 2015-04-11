class Complement
  @dna_map = { 
    'C' => 'G',
    'G' => 'C',
    'T' => 'A',
    'A' => 'U'
  }

  @rna_map = { 
    'C' => 'G',
    'G' => 'C',
    'U' => 'A',
    'A' => 'T'
  }

  def self.of_dna(string)
    replace_with_map(string, @dna_map)
  end

  def self.of_rna(string)
    replace_with_map(string, @rna_map)
  end

  def self.replace_with_map(string, map)
    output = []
    string.each_char do |char|
      output << map.fetch(char, char)
    end
    output.join
  end
end
