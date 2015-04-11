class Complement
  DNA_COMPLEMENTS = { "C" => "G", "G" => "C", "T" => "A", "A" => "U" }.freeze
  RNA_COMPLEMENTS = DNA_COMPLEMENTS.invert.freeze
  DNA_NUCLEOTIDE_REGEX = /[#{DNA_COMPLEMENTS.keys.join}]/
  RNA_NUCLEOTIDE_REGEX = /[#{RNA_COMPLEMENTS.keys.join}]/

  def self.of_dna(dna_strand)
    dna_strand.gsub(DNA_NUCLEOTIDE_REGEX, DNA_COMPLEMENTS)
  end

  def self.of_rna(rna_strand)
    rna_strand.gsub(RNA_NUCLEOTIDE_REGEX, RNA_COMPLEMENTS)
  end
end
