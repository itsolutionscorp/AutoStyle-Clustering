class Complement
  def self.of_dna(strand)
    dna2rna = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}
    raise ArgumentError if strand.split("").include? "U"
    strand.split("").map {|n|
      dna2rna[n]
    }.join
  end
  def self.of_rna(strand)
    rna2dna = {"G" => "C", "C" => "G", "A" => "T", "U" => "A"}
    raise ArgumentError if strand.split("").include? "T"
    strand.split("").map {|n|
      rna2dna[n]
    }.join
  end
end
