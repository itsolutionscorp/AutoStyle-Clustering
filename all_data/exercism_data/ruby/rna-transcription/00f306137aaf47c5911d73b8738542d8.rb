class Complement

  def self.of_dna(strand)
    common(strand)
  end

  def self.of_rna(strand)
    common(strand)
  end

  def self.common(strand)
    calling_method=caller[0][/`([^']*)'/, 1]
    nucleotide_complement={"G"=> "C", "C"=> "G", "T" => "A", "A" => "U"}
    nucleotide_complement=nucleotide_complement.invert if calling_method.eql? "of_rna"
    strand=strand.split(//)
    complements=strand.inject("") do|result, letter|
      result<< nucleotide_complement[letter]
    end
  end

end
