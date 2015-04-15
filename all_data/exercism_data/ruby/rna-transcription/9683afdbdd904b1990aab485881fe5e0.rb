class Complement
  def self.of_dna(dna)
    dna.each_char { |evaluated_char| 
      if evaluated_char == "G" then evaluated_char = "C"
      elsif evaluated_char == "C" then evaluated_char = "G"
      elsif evaluated_char == "T" then evaluated_char = "A"
      elsif evaluated_char == "A" then evaluated_char = "U"
      else puts "DNA Error"
      end
    }
    dna
  end
  def self.of_rna(rna)
    rna.each_char { |evaluated_char| 
      if evaluated_char == "C" then evaluated_char = "G"
      elsif evaluated_char == "G" then evaluated_char = "C"
      elsif evaluated_char == "A" then evaluated_char = "T"
      elsif evaluated_char == "U" then evaluated_char = "A"
      else puts "RNA Error"
      end
    }
    rna
  end
end

#* `G` -> `C`
#* `C` -> `G`
#* `T` -> `A`
#* `A` -> `U`
