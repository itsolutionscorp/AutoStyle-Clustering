class Complement
     COMPLEMENTS = {
      "G" => "C",
      "C" => "G",
      "T" => "A",
      "A" => "U"
      }

  def self.of_dna(nucleotide)
    rna = ""
    (0..(nucleotide.length - 1)).each do |index|
      rna = rna + COMPLEMENTS[nucleotide[index]]
    end
    rna
  end


  def self.of_rna (nucleotide)
    dna = ""
    (0..(nucleotide.length - 1)).each do |index|
      dna = dna + COMPLEMENTS.key(nucleotide[index])
    end
    dna
  end

end
