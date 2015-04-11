class Complement

  def self.of_dna (dna)
    dna.gsub!(/[GCTA]/, "G" => "C", "C" => "G", "T" => "A", "A" => "U") #Replaces matches within the regular expression with the corresponding argument.
  end

  def self.of_rna (rna)
    rna.gsub!(/[CGAU]/, "C" => "G", "G" => "C", "A" => "T", "U" => "A")
  end

  #initial thoughts were case statements - these work but gsub is much better.
  # def self.of_dna (dna)
  #   rna = ""
  #   dna.each_char do |x|
  #     case x
  #       when "G" 
  #         rna << "C"
  #       when "C" 
  #         rna << "G"
  #       when "T" 
  #         rna << "A"
  #       when "A" 
  #         rna << "U"
  #     end
  #   end
  #   rna
  # end

  # def self.of_rna (rna)
  #   dna = ""
  #   rna.each_char do |x|
  #     case x
  #       when "C" 
  #         dna << "G"
  #       when "G" 
  #         dna << "C"
  #       when "A" 
  #         dna << "T"
  #       when "U" 
  #         dna << "A"
  #     end
  #   end
  #   dna
  # end
end
