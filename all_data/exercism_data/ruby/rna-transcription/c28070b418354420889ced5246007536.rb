module Complement
  COMPLEMENT_MAPPING = {
      "G" => "C",
      "C" => "G",
      "T" => "A",
      "A" => "U"
    }

  def self.of_dna(dna_strand)
    dna_strand.chars.map{ |c| of_dna_nucleotide(c) }.join
  end

  def self.of_rna(dna_strand)
    dna_strand.chars.map{ |c| of_rna_nucleotide(c) }.join
  end

  def self.of_dna_nucleotide(dna_nucleotide)
    COMPLEMENT_MAPPING[dna_nucleotide]
  end

  def self.of_rna_nucleotide(dna_nucleotide)
    COMPLEMENT_MAPPING.invert[dna_nucleotide]
  end
end
