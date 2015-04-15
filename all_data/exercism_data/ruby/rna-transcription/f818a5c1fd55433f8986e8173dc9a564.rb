class Complement
  class << self
    def of_dna dna
      dna.chars.map { |dna_char| rna_of_dna_char(dna_char) }.join
    end

    def of_rna rna
      rna.chars.map { |rna_char| dna_of_rna_char(rna_char) }.join
    end
  end

private
  class << self
    def dna_of_rna_char rna_char
      {
        'C' => 'G',
        'G' => 'C',
        'A' => 'T',
        'U' => 'A'
      }[rna_char]
    end

    def rna_of_dna_char dna_char
      {
        'G' => 'C',
        'C' => 'G',
        'T' => 'A',
        'A' => 'U'
      }[dna_char]
    end
  end
end
