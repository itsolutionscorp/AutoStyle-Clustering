class Complement
  def self.of_dna(dna,message='')
    i = 0
    until i == dna.length
      if dna[i] == "G"
        message << ("C")
      elsif dna[i] =="C"
        message << ("G")
      elsif dna[i] =="T"
        message << ("A")
      else dna[i] == "A"
        message << ("U")
      end
      i+=1
    end

    return message
  end

  def self.of_rna(rna,message='')
    i = 0
    until i == rna.length
      if rna[i] == "C"
        message << ("G")
      elsif rna[i] =="G"
        message << ("C")
      elsif rna[i] =="A"
        message << ("T")
      else rna[i] == "U"
        message << ("A")
      end
      i+=1
    end

    return message
  end
end
