class Complement

  COMPLIMENTS = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}

  def self.of_dna(dna)
    rna = String.new()

    dna.each_char do |c|
      rna += COMPLIMENTS[c]
    end
    return rna
  end

  def self.of_rna(rna)
    dna = String.new()

    rna.each_char do |c|
      dna += COMPLIMENTS.key(c)
    end
    return dna
  end
end
