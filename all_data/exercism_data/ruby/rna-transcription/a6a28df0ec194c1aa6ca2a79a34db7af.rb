class Complement
  DNA_NUCLEOTIDES = %w(G C T A)
  RNA_NUCLEOTIDES = %w(C G A U)

  def self.of_dna(dna_string)
    find_complement(dna_string, DNA_NUCLEOTIDES, RNA_NUCLEOTIDES)
  end

  def self.of_rna(rna_string)
    find_complement(rna_string, RNA_NUCLEOTIDES, DNA_NUCLEOTIDES)
  end

  private

  def self.find_complement(string, source_array, final_array)
    complement_string = ''
    string.chars.each do |char|
      complement_string += final_array[source_array.index(char) || raise(ArgumentError)]
    end
    complement_string
  end
end
