class Complement
  COMP_MAP = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}
  INVERSE_COMP_MAP = COMP_MAP.invert

  def self.of_dna(dna)
    dna.chars.map{|char| COMP_MAP[char]}.join
  end

  def self.of_rna(rna)
    rna.chars.map{|char| INVERSE_COMP_MAP[char]}.join
  end
end
