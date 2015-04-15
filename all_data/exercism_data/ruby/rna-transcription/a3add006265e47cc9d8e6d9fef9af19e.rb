class Complement
  def self.of_dna(strand)
    find_complements strand, complements
  end

  def self.of_rna(strand)
    find_complements strand, complements.invert
  end

  def self.find_complements(strand, map)
    chars = strand.split ""
    response = []
    chars.each do |char|
      complement = map[char]
      response << complement
    end
    response.join
  end

  def self.complements
    {
      "C" => "G",
      "T" => "A",
      "A" => "U",
      "G" => "C"
    }
  end
end
