module Complement
  PAIRS = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}

  def self.of_dna dna
    dna.gsub /[#{PAIRS.keys}]/, PAIRS
  end

  def self.of_rna rna
    rna.gsub /[#{PAIRS.values}]/, PAIRS.invert
  end
end
