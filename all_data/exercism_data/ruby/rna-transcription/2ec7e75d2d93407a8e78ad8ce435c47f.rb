module Complement
  DNA_COMP = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  RNA_COMP = Hash[*DNA_COMP.flatten.reverse]

  def self.of_dna(rna)
    rna.to_s.chars.map{|c| DNA_COMP[c]}.join
  end

  def self.of_rna(dna)
    dna.to_s.chars.map{|c| RNA_COMP[c]}.join
  end
end
