class Complement

  RNA = { "C" => "G",
          "G" => "C",
          "T" => "A",
          "A" => "U" }

  DNA = { "C" => "G",
          "G" => "C",
          "U" => "A",
          "A" => "T" }

  def self.of_dna(letters)
    letters.tr(RNA.keys.join, RNA.values.join)
  end

  def self.of_rna(letters)
    letters.tr(DNA.keys.join, DNA.values.join)
  end
end
