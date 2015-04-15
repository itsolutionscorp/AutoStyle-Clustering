class Complement
  class << self
    def of_dna(string)
      convert_string(string, :dna_to_rna)
    end

    def of_rna(string)
      convert_string(string, :rna_to_dna)
    end

    def nucleotide_map
      {
        'G' => 'C',
        'C' => 'G',
        'T' => 'A',
        'A' => 'U'
      }
    end

    def dna_to_rna(nucleotide)
      nucleotide_map[nucleotide]
    end

    def rna_to_dna(nucleotide)
      nucleotide_map.invert[nucleotide]
    end

    private

    def convert_string(string, method)
      string.chars.map { |c| self.send(method, c) }.join
    end
  end
end
