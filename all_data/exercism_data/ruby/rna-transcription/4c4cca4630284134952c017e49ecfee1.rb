class Complement
  COMP_MAP = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}
  INVERSE_COMP_MAP = COMP_MAP.invert

  def self.of_dna(dna)
    dna.scan(/./).map{|char| COMP_MAP[char]}.join
  end

  def self.of_rna(rna)
    rna.scan(/./).map{|char| INVERSE_COMP_MAP[char]}.join
  end
end
