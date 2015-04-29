class Complement
  def self.of_dna(strand)
    strand.chars.each_index do |i|
      case strand[i]
      when "G"
        strand[i] = "C"
      when "C"
        strand[i] = "G"
      when "T"
        strand[i] = "A"  
      else
        strand[i] =  "U"
      end
    end
    return strand
  end

  def self.of_rna(strand)
    strand.chars.each_index do |i|
      case strand[i]
      when "C"
        strand[i] = "G"
      when "G"
        strand[i] = "C"
      when "A"
        strand[i] = "T"  
      else
        strand[i] =  "A"
      end
    end
    return strand
  end

end
