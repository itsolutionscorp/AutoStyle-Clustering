class Complement

 Nucleotide_complement={"G"=> "C", "C"=> "G", "T" => "A", "A" => "U"}


  def self.of_dna(strand)
    strand=strand.split(//)
    complements=strand.inject("") {|result, letter|
      result<< Nucleotide_complement[letter]
    }
  end #End of_dna

  def self.of_rna(strand)
    strand=strand.split(//)
    complements=strand.inject("") {|result, letter|
      result<< Nucleotide_complement.invert[letter]
    }
  end #End of_rna



end #End of class
