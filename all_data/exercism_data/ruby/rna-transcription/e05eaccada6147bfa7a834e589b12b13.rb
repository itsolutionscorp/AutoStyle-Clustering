class Complement

  DNA_translation = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }

  def self.of_dna(nucleotide)
    nucleotide.split('')
      .map {|letter| DNA_translation[letter]}
      .join
  end

  def self.of_rna(nucleotide)
    nucleotide.split('')
      .map {|letter| DNA_translation.invert[letter]}
      .join
  end

end
