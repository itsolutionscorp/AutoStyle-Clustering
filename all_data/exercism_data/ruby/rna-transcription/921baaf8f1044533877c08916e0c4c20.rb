class Complement

# check for T or U first
# then define a single method for the loop

  def self.of_dna nucleotides
    transcript = nucleotides.chars.map do |nt|
      if nt == "C"
        "G"
      elsif nt == "G"
        "C"
      elsif nt == "T"
        "A"
      elsif nt == "A"
        "U"
      elsif nt == "U"
        raise ArgumentError
      end
    end
    transcript.join
  end

  def self.of_rna nucleotides
    transcript = nucleotides.chars.map do |nt|
      if nt == "C"
        "G"
      elsif nt == "G"
        "C"
      elsif nt == "T"
        raise ArgumentError
      elsif nt == "A"
        "T"
      elsif nt == "U"
        "A"
      end
    end
    transcript.join
  end

end
