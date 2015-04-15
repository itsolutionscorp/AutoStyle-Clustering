module Complement

  def Complement.of_dna(seq)
    seq.split('').map{ |e| 
      Complement.dna_complement e
    }.join
  end
  
  def Complement.of_rna(seq) 
    seq.split('').map{ |e| 
      Complement.rna_complement e
    }.join
  end

  def Complement.dna_complement(rna)
    return {
      "C" => "G",
      "G" => "C",
      "T" => "A",
      "A" => "U"
    }[rna] 
  end

  def Complement.rna_complement(dna)
    return {
      "G" => "C",
      "C" => "G",
      "A" => "T",
      "U" => "A"
    }[dna]
  end

end
