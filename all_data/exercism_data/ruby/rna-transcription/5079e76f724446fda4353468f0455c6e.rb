module Complement
  DNA_COMP = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  RNA_COMP = DNA_COMP.invert

  def self.of_dna(nucleotides)
    nucleotides.to_s.chars.map{|c| DNA_COMP[c]}.join
  end

  def self.of_rna(nucleotides)
    nucleotides.to_s.chars.map{|c| RNA_COMP[c]}.join
  end
end
