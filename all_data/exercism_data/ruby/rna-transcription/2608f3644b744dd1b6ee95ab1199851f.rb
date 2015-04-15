class Complement
  def self.of_dna(strand)
    strand.chars.map do |n|
      complements[n]
    end.join
  end

  def self.of_rna(strand)
    strand.chars.map do |n|
      complements.key(n)
    end.join
  end

  private

  def self.complements
    { "C" => "G",
      "G" => "C",
      "T" => "A",
      "A" => "U" }
  end
end
