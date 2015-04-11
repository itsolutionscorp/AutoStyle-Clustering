module Complement

  RULES = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U",
  }

  def self.of_dna(strand)
    complement(strand, RULES)
  end

  def self.of_rna(strand)
    complement(strand, RULES.invert)
  end

  private
  def self.complement(strand, rules)
    strand.chars.map { |_nucleotide|
      rules[_nucleotide]
    }.join
  end
end
