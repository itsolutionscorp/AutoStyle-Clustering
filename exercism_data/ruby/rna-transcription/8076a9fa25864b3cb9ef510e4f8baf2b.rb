class Complement
  def Complement.of_dna (rna)
    rna.chars.map do |c|
      case c
      when "G" then "C"
      when "C" then "G"
      when "T" then "A"
      when "A" then "U"
      else
        raise ArgumentError.new("Illegal character in RNA-string.")
      end
    end.join
  end
  def Complement.of_rna (dna)
    dna.chars.map do |c|
      case c
      when "G" then "C"
      when "C" then "G"
      when "A" then "T"
      when "U" then "A"
      else
        raise ArgumentError.new("Illegal character in DNA-string.")
      end
    end.join
  end
end
