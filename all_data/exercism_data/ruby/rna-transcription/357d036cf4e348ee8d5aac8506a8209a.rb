class Complement

  def self.of_dna(str)

    complements = {
      "T" => 'A',
      "C" => 'G',
      "G" => "C",
      "A" => "U"
    }

    self.complement(str, complements)
  end

  def self.of_rna(str)

    complements = {
      "A" => 'T',
      "G" => 'C',
      "C" => "G",
      "U" => "A"
    }

    self.complement(str, complements)
  end



  def self.complement(str, complements)
    
    new_strand = ""

    str.each_char do |char|
      new_strand += complements[char]
    end

    new_strand    
  end

end
