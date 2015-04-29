class Complement

  COMPLEMENTS_OF_DNA = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }

  def self.of_dna(dna_strand)
    dna_strand.split("").reduce("") do |rna_strand, nucleotide|
      rna_strand + COMPLEMENTS_OF_DNA[nucleotide]
    end
  end

  def self.of_rna(rna_strand)
    rna_strand.split("").reduce("") do |dna_strand, nucleotide|
      dna_strand + COMPLEMENTS_OF_DNA.key(nucleotide)
    end
  end

end
