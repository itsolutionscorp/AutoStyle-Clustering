class Complement
  def self.of_dna(dna)
    replace(dna)
    return dna.gsub("A","U").gsub("T","A")
  end

  def self.of_rna(rna)
    replace(rna)
    return rna.gsub("A","T").gsub("U","A")
  end

  def self.replace(str)
    for i in 0..str.length
      if str[i] == "C"
        str[i] = "G"
      elsif str[i] == "G"
        str[i]="C"
      end
    end
  end

end
