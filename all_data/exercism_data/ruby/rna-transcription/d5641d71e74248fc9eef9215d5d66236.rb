class Complement

  COMPLEMENTS = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }

  def self.of_dna(strand)
    rna = ""

    strand.each_char do |chr|
      rna += COMPLEMENTS[chr]
    end

    rna
  end

  def self.of_rna(strand)
    dna = ""

    strand.each_char do |chr|
      dna += COMPLEMENTS.key(chr)
    end

    dna
  end

end
