class Complement

  def self.of_dna(dna)
    return 'C' if dna == 'G'
    return 'G' if dna == 'C'
    return 'A' if dna == 'T'
    return 'U' if dna == 'A'

    if dna.is_a? String then
      complement = dna.split(//).map do |nuc|
        case (nuc)
          when 'G'
            'C'
          when 'C'
            'G'
          when 'T'
            'A'
          when 'A'
            'U'
        end
      end
      return complement.join
    end
  end

  def self.of_rna(rna)
    return 'C' if rna == 'G'
    return 'G' if rna == 'C'
    return 'A' if rna == 'U'
    return 'T' if rna == 'A'

    if rna.is_a? String then
      complement = rna.split(//).map do |nuc|
        case (nuc)
          when 'G'
            'C'
          when 'C'
            'G'
          when 'U'
            'A'
          when 'A'
            'T'
        end
      end
      return complement.join
    end
  end

end
