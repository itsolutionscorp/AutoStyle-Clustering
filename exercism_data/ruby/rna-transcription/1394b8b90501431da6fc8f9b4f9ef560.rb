class Complement

  RNA_COMPLEMENT={
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }

  DNA_COMPLEMENT={
    "G" => "C",
    "C" => "G",
    "U" => "A",
    "A" => "T"
  }

  def self.of_dna(nucleotide)
    split_strand = nucleotide.chars
    complement = split_strand.map {|nucleo| RNA_COMPLEMENT[nucleo] }
    complement.join
  end

  def self.of_rna(nucleotide)
    split_strand = nucleotide.chars
    complement = split_strand.map { |nucleo| DNA_COMPLEMENT[nucleo] }
    complement.join
  end

end
