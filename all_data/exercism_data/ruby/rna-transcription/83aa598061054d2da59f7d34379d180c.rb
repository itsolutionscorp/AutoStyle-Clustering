class Complement
  TABLE = {"C" => "G", 'G' => 'C', 'T' => 'A', 'A' => 'U'}
  def self.of_dna(dna)
    self.perform(dna, TABLE)
  end

  def self.of_rna(rna)
    self.perform(rna, TABLE.invert)
  end

  def self.perform(string, table)
    string.chars.map {|char| table[char] }.join
  end
end
