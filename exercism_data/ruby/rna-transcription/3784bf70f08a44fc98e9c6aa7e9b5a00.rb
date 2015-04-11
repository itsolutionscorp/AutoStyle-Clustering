class Complement
  
  def self.of_dna(rna_sequence)
    rna_sequence.chars.map do |nucleotide|
      case nucleotide
        when 'C'
          'G'
        when 'T'
          'A'
        when 'A'
          'U'
        else
          'C'
      end
    end.join("")
  end

  def self.of_rna(rna_sequence)
    rna_sequence.chars.map do |nucleotide|
      case nucleotide
        when 'C'
          'G'
        when 'A'
          'T'
        when 'U'
          'A'
        else
          'C'
      end
    end.join("")
  end
end
