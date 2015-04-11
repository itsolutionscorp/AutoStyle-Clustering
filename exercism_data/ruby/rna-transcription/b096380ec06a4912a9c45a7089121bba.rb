class Complement

  def self.of_dna(str)
    dna_keys = {
      "G" => "C",
      "C" => "G",
      "T" => "A",
      "A" => "U"
    }
    result = ""
    str.each_char { |l| result << dna_keys[l] }
    result
  end

  def self.of_rna(str)
    rna_keys = {
      "C" => "G",
      "G" => "C",
      "A" => "T",
      "U" => "A"
    }
    result = ""
    str.each_char { |l| result << rna_keys[l] }
    result
  end

end
