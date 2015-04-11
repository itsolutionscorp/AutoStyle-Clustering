class Complement
  def self.mapping
    @map = {"G" => "C",
            "C" => "G",
            "T" => "A",
            "A" => "U"}
  end

  def self.of_dna(string)
    mapping
    string.split("").map{|x| @map[x] }.join("")
  end

  def self.of_rna(string)
    @map = mapping.invert
    string.split("").map{|x| @map[x] }.join("")
  end
end
