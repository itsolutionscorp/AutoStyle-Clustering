class Complement

  def self.of_dna(strand)
    nucleotide_complement={"G"=> "C", "C"=> "G", "T" => "A", "A" => "U"}
    common_nucleotide(strand, nucleotide_complement)
  end #End of_dna

  def self.of_rna(strand)
    nucleotide_complement={"G"=> "C", "C"=> "G", "T" => "A", "A" => "U"}.invert
    common_nucleotide(strand, nucleotide_complement)
  end #End of_rna

  def self.common_nucleotide(strand, nucleotide_complement)
    complements=""
    strand.each_char {|c| complements+= nucleotide_complement[c] }
    complements
  end #End common

end #End of class
