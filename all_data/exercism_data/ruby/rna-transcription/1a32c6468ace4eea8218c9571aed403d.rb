class Complement
  def self.of_dna(strand)
    new_strand = strand.chars.map! do |letter|
      if letter == "G"
        "C"
      elsif letter == "C"
        "G"
      elsif letter == "T"
        "A"
      else
        "U"
      end
    end
    new_strand.join
  end

  def self.of_rna(strand)
    new_strand = strand.chars.map! do |letter|
      if letter == "C"
        "G"
      elsif letter == "G"
        "C"
      elsif letter == "U"
        "A"
      else
        "T"
      end
    end
    new_strand.join
  end

end
