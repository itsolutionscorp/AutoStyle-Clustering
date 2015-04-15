class Complement
  DNA_COMPLEMENTS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  RNA_COMPLEMENTS = DNA_COMPLEMENTS.invert

  class << self
    def of_dna(dna)
      dna.chars.map { |nucleotide| DNA_COMPLEMENTS[nucleotide] }.join
    end

    def of_rna(rna)
      rna.chars.map { |nucleotide| RNA_COMPLEMENTS[nucleotide] }.join
    end
  end
end
