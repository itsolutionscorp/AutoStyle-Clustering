class Complement
  COMPLEMENTS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U',
  }
  class << self
    def of_dna(strand)
      strand.chars.map { |nucleotide| COMPLEMENTS[nucleotide] }.join
    end

    def of_rna(strand)
      strand.chars.map { |nucleotide| COMPLEMENTS.key(nucleotide) }.join
    end
  end
end
