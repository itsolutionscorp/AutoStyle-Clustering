class Complement
  @dna_rna = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}
  @rna_dna = @dna_rna.invert

  def self.of_dna key
    convert @dna_rna, key
  end

  def self.of_rna key
    convert @rna_dna, key
  end

  def self.convert dict, key
    working_str = ""
    key.each_char {|char| working_str += dict[char]}
    working_str
  end
end
