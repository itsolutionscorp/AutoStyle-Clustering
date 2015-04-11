class Complement

  def self.of_dna(nucleotide)
        complements = {
      "G" => "C",
      "C" => "G",
      "T" => "A",
      "A" => "U"
      }

    size = nucleotide.length
    rna = ""
    if size == 1
      rna = complements[nucleotide]
    else
      (0..(nucleotide.length - 1)).each do |index|
        rna = rna + complements[nucleotide[index]]
      end
    end
    rna
  end

  def self.of_rna (nucleotide)
        complements = {
      "G" => "C",
      "C" => "G",
      "T" => "A",
      "A" => "U"
      }
    size = nucleotide.length
    dna = ""
    if size == 1
      dna = complements.key(nucleotide)
    else
      (0..(nucleotide.length - 1)).each do |index|
        dna = dna + complements.key(nucleotide[index])
      end
    end
    dna
  end


end
