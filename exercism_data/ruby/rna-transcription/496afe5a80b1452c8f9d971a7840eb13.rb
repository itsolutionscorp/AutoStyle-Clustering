class Complement
  def self.of_dna string
    string.gsub(/\w/) do |letter|
      @complements[letter]
    end
  end

  def self.of_rna string
    string.gsub(/\w/) do |letter|
      @complements_inverted[letter]
    end
  end

  @complements = {  # DNA => RNA
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }
  @complements_inverted = {  # RNA => DNA
    "C" => "G",
    "G" => "C",
    "A" => "T",
    "U" => "A"
  }
end
