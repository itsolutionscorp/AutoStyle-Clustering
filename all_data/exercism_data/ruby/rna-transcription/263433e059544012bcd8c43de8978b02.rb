class Complement

  LOOKUP = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}

  def self.of_dna(string)
    string.chars.map{|c| LOOKUP[c] }.join
  end

  def self.of_rna(string)
    string.chars.map{ |c| LOOKUP.invert[c] }.join
  end

end
