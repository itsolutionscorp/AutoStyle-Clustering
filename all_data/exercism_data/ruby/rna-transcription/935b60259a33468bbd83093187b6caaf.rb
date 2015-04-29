class Complement
  DNA_RNA_COMPLEMENTS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U',
  }

  RNA_DNA_COMPLEMENTS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'U' => 'A',
    'A' => 'T',
  }

  class << self
    def of_dna dna_string
      dna = dna_string.chars
      transcribe dna, DNA_RNA_COMPLEMENTS
    end

    def of_rna rna_string
      rna = rna_string.chars
      transcribe rna, RNA_DNA_COMPLEMENTS
    end

    private

    def transcribe nucleotides, complements
      nucleotides.map{ |nucleotide| complements[nucleotide] }.reduce(:+)
    end
  end
end
