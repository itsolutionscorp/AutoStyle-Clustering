module Complement

  DICTIONARY = {"C" => "G",
                "G" => "C",
                "T" => "A",
                "A"=> "U"}

  def self.of_dna(dna_strand)
    dna_strand.gsub(/[ACGTU]/, DICTIONARY)
  end

  def self.of_rna(rna_strand)
    rna_strand.gsub(/[ACGTU]/, DICTIONARY.invert)
  end
end
