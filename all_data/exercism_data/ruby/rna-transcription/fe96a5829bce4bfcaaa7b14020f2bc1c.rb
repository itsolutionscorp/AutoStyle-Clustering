class Complement

  DNA_NUCLEOTIDES = "ACGT"
  RNA_NUCLEOTIDES = "UGCA"

  def self.of_dna(dna)
    dna.split('').map do |nucleotide|
      RNA_NUCLEOTIDES[DNA_NUCLEOTIDES.index(nucleotide)]
    end.join('')
  end

  def self.of_rna(rna)
    rna.split('').map do |nucleotide|
      DNA_NUCLEOTIDES[RNA_NUCLEOTIDES.index(nucleotide)]
    end.join('')
  end

end
