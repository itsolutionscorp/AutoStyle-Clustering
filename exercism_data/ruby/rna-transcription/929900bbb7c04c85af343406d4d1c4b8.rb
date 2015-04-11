class Complement
  def self.of_dna(dna)
    rna = dna.gsub(/G|C|T|A/,
           "G" => "C",
           "C" => "G",
           "T" => "A",
           "A" => "U" )
    return rna
  end

  def self.of_rna(rna)
    dna = rna.gsub(/C|G|A|U/,
           "C" => "G",
           "G" => "C",
           "A" => "T",
           "U" => "A" )
    return dna
  end
end
