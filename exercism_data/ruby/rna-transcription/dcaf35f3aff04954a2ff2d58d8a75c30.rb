class Complement
  #complements hash:  DNA key => RNA value
  DNA_to_RNA = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}

  def self.of_dna(rna)
    rna_complement = ""
    rna.each_char { |nucleotide| rna_complement << DNA_to_RNA[nucleotide] }
    rna_complement
  end

  def self.of_rna(dna)
    dna_complement = ""
    dna.each_char { |nucleotide| dna_complement << DNA_to_RNA.key(nucleotide) }
    dna_complement
  end
end
