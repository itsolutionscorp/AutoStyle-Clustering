DNA_TRANSFORM = {
  "C" => "G",
  "G" => "C",
  "T" => "A",
  "A" => "U"
}

RNA_TRANSFORM = {
  "C" => "G",
  "G" => "C",
  "A" => "T",
  "U" => "A"
}

class Complement
  def self.of_dna(string)
    self.transform(DNA_TRANSFORM, string).join
  end

  def self.of_rna(string)
    self.transform(RNA_TRANSFORM, string).join
  end

  def self.transform(hash, string)
    completed = []
    string.chars.map do |letter|
      completed << hash[letter]
    end
    completed
  end
end
