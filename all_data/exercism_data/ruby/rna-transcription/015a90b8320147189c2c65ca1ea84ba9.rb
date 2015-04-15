class Complement

  def self.of_dna(dna)
    rna = []

    dna_array = dna.split(//)

    dna_array.each do |dna|

      case dna
      when "A"
        rna << "U"
      when "T"
        rna << "A"
      when "G"
        rna << "C"
      when "C"
        rna << "G"
      else

      end
    end
    
  return rna.join

  end


  def self.of_rna(rna)
    dna = []

    rna_array = rna.split(//)

    rna_array.each do |rna|

      case rna
      when "A"
        dna << "T"
      when "U"
        dna << "A"
      when "G"
        dna << "C"
      when "C"
        dna << "G"
      else

      end
    end
    
  return dna.join

  end
end
