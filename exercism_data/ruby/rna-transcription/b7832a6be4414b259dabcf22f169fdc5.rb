module Complement

  DNA_TO_RNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }.freeze

  RNA_TO_DNA = DNA_TO_RNA.invert.freeze

  class << self

    def of_dna(dna)
      transcribe dna, DNA_TO_RNA
    end

    def of_rna(rna)
      transcribe rna, RNA_TO_DNA
    end

    def transcribe(strand, complements)
      strand.chars.map { |nucleotide| complements[nucleotide] }.join
    end

  end

end
