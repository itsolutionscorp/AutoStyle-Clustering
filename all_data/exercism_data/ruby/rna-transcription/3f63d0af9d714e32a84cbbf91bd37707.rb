class Complement

  DNA_TO_RNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  RNA_TO_DNA = {
    'C' => 'G',
    'G' => 'C',
    'A' => 'T',
    'U' => 'A'
  }

  def self.of_dna(dna_string)
    map_string(dna_string, DNA_TO_RNA)
  end

  def self.of_rna(rna_string)
    map_string(rna_string, RNA_TO_DNA)
  end

  def self.map_string(string, string_map)
    string.chars.map {|code| string_map[code]}.join
  end

end
