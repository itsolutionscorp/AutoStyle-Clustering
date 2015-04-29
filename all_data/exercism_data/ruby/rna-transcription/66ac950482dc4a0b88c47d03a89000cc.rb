module Complement
  def self.of_dna(rna)
    raise ArgumentError if rna =~ /[^CGTA]/
    rna.tr("CGTA", "GCAU")
  end

  def self.of_rna(dna)
    raise ArgumentError if dna =~ /[^GCAU]/
    dna.tr("GCAU", "CGTA")
  end
end
