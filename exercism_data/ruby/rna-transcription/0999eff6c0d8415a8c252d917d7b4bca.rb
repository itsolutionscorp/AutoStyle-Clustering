class Complement
  def self.of_dna(dna_string)
    @rna_string = ""
    dna_string.each_char do |n|
      case n
      when "G"
        @rna_string += "C"
      when "C"
        @rna_string += "G"
      when "T"
        @rna_string += "A"
      when "A"
        @rna_string += "U"
      else
        @rna_string += "X"
      end
    end
    @rna_string
  end


  def self.of_rna(rna_string)
    @dna_string = ""
    rna_string.each_char do |n|
      case n
      when "C"
        @dna_string += "G"
      when "G"
        @dna_string += "C"
      when "A"
        @dna_string += "T"
      when "U"
        @dna_string += "A"
      else
        @dna_string += "X"
      end
    end
    @dna_string
  end
end

Complement.of_dna("C")
