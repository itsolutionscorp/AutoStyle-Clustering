class Complement

  def self.of_dna(nucleotides)
    complements = nucleotides.chars.map do |nucleotide|
      case nucleotide
        when 'C'
          'G'
        when 'G'
          'C'
        when 'T'
          'A'
        when 'A'
          'U'
      end
    end
    return complements.join('')
  end

  def self.of_rna(nucleotides)
    complements = nucleotides.chars.map do |nucleotide|
      case nucleotide
        when 'C'
          'G'
        when 'G'
          'C'
        when 'A'
          'T'
        when 'U'
          'A'
      end
    end
    return complements.join('')
  end

end
