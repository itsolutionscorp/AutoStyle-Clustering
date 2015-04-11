class Complement

 NUCLEOTIDE_COMPLEMENT={"G"=> "C", "C"=> "G", "T" => "A", "A" => "U"}

  def self.of_dna(strand)
    strand=strand.split(//)
    complements=strand.inject("") do|result, letter|
      result<< NUCLEOTIDE_COMPLEMENT[letter]
    end
  end

  def self.of_rna(strand)
    strand=strand.split(//)
    complements=strand.inject("") do|result, letter|
      result<< NUCLEOTIDE_COMPLEMENT.invert[letter]
    end
  end

end
