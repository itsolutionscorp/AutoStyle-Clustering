class Complement
  def self.of_dna(dna)
    dna.chars.map { |el|
      {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}[el]
    }.join
  end

  def self.of_rna(rna)
    rna.chars.map { |el|
      {"G" => "C", "C" => "G", "U" => "A", "A" => "T"}[el]
    }.join
  end
end
