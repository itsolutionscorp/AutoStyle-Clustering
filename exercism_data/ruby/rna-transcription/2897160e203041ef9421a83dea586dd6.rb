class Complement

  def self.of_dna(nucleotide)
    dna_translation = {
      "G" => "C",
      "C" => "G",
      "T" => "A",
      "A" => "U"
    }
    p nucleotide.split('').map {|letter| dna_translation[letter]}.join
  end

  def self.of_rna(nucleotide)
    rna_translation = {
      "C" => "G",
      "G" => "C",
      "A" => "T",
      "U" => "A"
    }
    nucleotide.split('').map {|letter| rna_translation[letter]}.join
  end

end
