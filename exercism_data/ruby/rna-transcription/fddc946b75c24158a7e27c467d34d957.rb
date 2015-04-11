class Complement
  NUCLEOTIDES = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U",
  }

  def self.of_dna(rna)
    complement = ""
    rna.each_char {|x| complement << NUCLEOTIDES[x] }
    complement
  end

  def self.of_rna(dna)
    complement = ""
    dna.each_char {|x| complement << NUCLEOTIDES.key(x) }
    complement
  end
end
