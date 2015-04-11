class Complement

  COMPLIMENTS = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}

  def self.of_dna(dna)
    return dna.chars.map{ |c| COMPLIMENTS[c] }.join
  end

  def self.of_rna(rna)
    return rna.chars.map{ |c| COMPLIMENTS.key(c) }.join
  end
end
