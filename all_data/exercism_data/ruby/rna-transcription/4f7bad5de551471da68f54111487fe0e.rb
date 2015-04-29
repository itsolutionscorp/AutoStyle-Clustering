class Complement
  DNA_NUCLEOTIDES = "GCTA"
  RNA_COMPLEMENTS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U',
  }

  RNA_NUCLEOTIDES = "CGAU"
  DNA_COMPLEMENTS = {
    'C' => 'G',
    'G' => 'C',
    'A' => 'T',
    'U' => 'A',
  }


  class << self
    def of_dna dna
      dna.gsub(/[#{DNA_NUCLEOTIDES}]/, RNA_COMPLEMENTS)
    end

    def of_rna rna
      rna.gsub(/[#{RNA_NUCLEOTIDES}]/, DNA_COMPLEMENTS)
    end
  end
end
