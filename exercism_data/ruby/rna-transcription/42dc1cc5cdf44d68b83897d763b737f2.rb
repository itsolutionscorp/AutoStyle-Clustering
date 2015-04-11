class Complement
  COMPLEMENTS = {
      "C" => "G",
      "G" => "C",
      "T" => "A",
      "A" => "U" }

  def self.of_dna(strand)
    strand.chars.map do |n|
      COMPLEMENTS[n]
    end.join
  end

  def self.of_rna(strand)
    strand.chars.map do |n|
      COMPLEMENTS.key(n)
    end.join
  end

end
