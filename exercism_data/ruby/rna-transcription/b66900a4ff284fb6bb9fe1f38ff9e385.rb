class Complement
  
  def self.of_dna (dna_strand)
    @intermediate_strand = dna_strand.chars.map {|x|
      case
      when x == "C"
        x.tr("C", "J")
      when x == "G"
        x.tr("G", "I")
      when x == "T"
        x.tr("T", "A") 
      else
        x.tr("A", "U")
    end
  }

  @final_result = @intermediate_strand.map {|x|
    case 
    when x == "J"
      x.tr("J", "G")
    when x == "I"
      x.tr("I", "C")
    else x
    end
  }
  
  @rna_strand = @final_result.join
  @rna_strand
end

  def self.of_rna(rna_strand)
    @intermediate_strand2 = rna_strand.chars.map {|x|
      case
      when x == "C"
        x.tr("C", "J")
      when x == "G"
        x.tr("G", "I")
      when x == "A"
        x.tr("A", "T") 
      else
        x.tr("U", "A")
    end
  }

    @final_result2 = @intermediate_strand2.map {|x|
    case 
     when x == "J"
      x.tr("J", "G")
      when x == "I"
      x.tr("I", "C")
      else x
      end
  }
  
  @dna_strand2 = @final_result2.join
  @dna_strand2
  end
end
