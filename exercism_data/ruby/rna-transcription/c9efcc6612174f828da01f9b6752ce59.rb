class Complement

  def self.of_rna(str)
    map_input(str, dna_map)
  end

  def self.of_dna(str)
    map_input(str, rna_map)
  end

  private

  def self.map_input(str, map)
    input = to_array(str)
    output = ''
    input.map {|v| output += map[v]}
    output
  end

  def self.to_array(str)
    str.split("")
  end

  def self.rna_map
    {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }
  end

  def self.dna_map
    {
      'C' => 'G',
      'G' => 'C',
      'A' => 'T',
      'U' => 'A'
    }
  end
end
