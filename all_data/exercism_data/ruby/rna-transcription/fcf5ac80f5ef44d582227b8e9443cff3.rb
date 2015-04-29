class Complement
  def self.of_dna(strand)
    strand = strand.split("")
    comp = []
    strand.each{|x|
      if x == "G"
        comp << "C"
      elsif x == "C"
        comp << "G"
      elsif x == "T"
        comp << "A"
      elsif x == "A"
        comp << "U"
      end
    }
    comp.join("")
  end

  def self.of_rna(strand)
    strand = strand.split("")
    comp = []
    strand.each{|x|
      if x == "G"
        comp << "C"
      elsif x == "C"
        comp << "G"
      elsif x == "U"
        comp << "A"
      elsif x == "A"
        comp << "T"
      end
    }
    comp.join("")
  end
end

Complement.of_rna('C')
