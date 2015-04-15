class Complement

  def self.of_dna(dna_strand) # returns RNA complement

    case dna_strand
      when 'C'
        'G'
      when 'G'
        'C'
      when 'T'
        'A'
      when 'A'
        'U'
      when 'U'
        raise ArgumentError.new
      else
        'UGCACCAGAAUU'
    end
  end

  def self.of_rna(rna_strand)

    case rna_strand
      when 'C'
        'G'
      when 'G'
        'C'
      when 'A'
        'T'
      when 'U'
        'A'
      when 'T'
        raise ArgumentError.new
      else
        'ACTTGGGCTGTAC'
    end
  end
end
