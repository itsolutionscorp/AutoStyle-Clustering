module Complement

  def self.of_dna(nucleotide_string)
    dna_complements = {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }

    nucleotide_string.chars.map do |nucleotide| 
      if dna_complements[nucleotide].nil?
        raise ArgumentError
      end

      dna_complements[nucleotide]
    end.join
  end

  def self.of_rna(nucleotide_string)
    rna_complements = {
      'C' => 'G',
      'G' => 'C',
      'A' => 'T',
      'U' => 'A'
    }

    nucleotide_string.chars.map do |nucleotide| 
      if rna_complements[nucleotide].nil?
        raise ArgumentError
      end

      rna_complements[nucleotide]
    end.join
  end
end
