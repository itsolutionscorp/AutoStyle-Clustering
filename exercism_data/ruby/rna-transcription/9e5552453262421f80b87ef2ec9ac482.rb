class Complement
  DNA_NUCLEOTIDES = "GCTA"
  RNA_NUCLEOTIDES = "CGAU"

  def self.of_dna(dna_string)
    find_complement(dna_string, DNA_NUCLEOTIDES, RNA_NUCLEOTIDES)
  end

  def self.of_rna(rna_string)
    find_complement(rna_string, RNA_NUCLEOTIDES, DNA_NUCLEOTIDES)
  end

  private

  def self.find_complement(string, source_array, complements_array)
    raise ArgumentError, "Sequence contains invalid characters" unless string.delete(source_array).empty?
    string.tr(source_array, complements_array)
  end
end
