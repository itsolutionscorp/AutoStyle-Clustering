class Complement
  # Split in two hashes and give clear name
  COMPLEMENT_OF_DNA = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }

  def self.of_dna(string)
    new.of_dna(string)
  end

  def self.of_rna(string)
    new.of_rna(string)
  end

  def of_dna(string)
    string.chars.map { |nucleotide| COMPLEMENT_OF_DNA[nucleotide] }.join
  end

  def of_rna(string)
    string.chars.map { |nucleotide| COMPLEMENT_OF_DNA.invert[nucleotide] }.join
  end
end
