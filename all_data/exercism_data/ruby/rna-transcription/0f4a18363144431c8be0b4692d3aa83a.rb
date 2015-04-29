class Complement

  def self.of_dna(strand)
    common(strand, "dna")
  end

  def self.of_rna(strand)
    common(strand, "rna")
  end

  def self.common(strand, type)
    nucleotide_complement={"G"=> "C", "C"=> "G", "T" => "A", "A" => "U"}.invert if type.eql? "rna"
    nucleotide_complement={"G"=> "C", "C"=> "G", "T" => "A", "A" => "U"} if type.eql? "dna"
    strand=strand.split(//)
    complements=strand.inject("") do|result, letter|
      result<< nucleotide_complement[letter]
    end
  end

end
