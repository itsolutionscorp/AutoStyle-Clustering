class Complement
  DNA_TO_RNA_DICTIONARY = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }

  def self.of_dna(dna_string)
    convert(dna_string, DNA_TO_RNA_DICTIONARY)
  end

  def self.of_rna rna_string
    convert(rna_string, DNA_TO_RNA_DICTIONARY.invert)
  end

  def self.convert(initial_strand, dictionary)
    output = ""
    initial_strand.length.times { |i| output += dictionary[initial_strand[i]] }
    output
  end
end
