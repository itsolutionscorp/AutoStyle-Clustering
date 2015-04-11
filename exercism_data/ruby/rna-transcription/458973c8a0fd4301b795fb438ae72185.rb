class Complement

  def self.of_dna(strand)
    strand.gsub(NUCLEOTIDE, DNA_TO_RNA )
  end

  def self.of_rna(strand)
    strand.gsub(NUCLEOTIDE, RNA_TO_DNA )
  end

  private

  NUCLEOTIDE = /[CGTAU]/

  DNA_TO_RNA = {
    'C' => 'G',
    'G' => 'C',
    'T' => 'A',
    'A' => 'U'
  }

  RNA_TO_DNA = DNA_TO_RNA.invert 
  
end
