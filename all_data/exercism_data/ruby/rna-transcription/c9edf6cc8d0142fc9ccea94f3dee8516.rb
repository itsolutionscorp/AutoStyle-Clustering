module Complement
  DNA_COMP = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  RNA_COMP = DNA_COMP.invert

  def self.of_dna(nucleotides)
    transliterate(nucleotides, DNA_COMP)
  end

  def self.of_rna(nucleotides)
    transliterate(nucleotides, RNA_COMP)
  end

  private

  def self.transliterate(string, lookup_hash)
    string.to_s.chars.map{|c| lookup_hash[c]}.join
  end
end
