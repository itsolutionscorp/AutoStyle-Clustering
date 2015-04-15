class Complement

  def self.of_dna(rna)
    strand = ''
    rna.length.times { |i|
    case rna[i]
      when 'C'
        strand << 'G'
      when 'G'
        strand << 'C'
      when 'T'
        strand << 'A'
      when 'A'
        strand << 'U'
    end
    }
    strand
  end

  def self.of_rna(dna)
    strand = ''
    dna.length.times { |i|
      case dna[i]
        when 'C'
          strand << 'G'
        when 'G'
          strand << 'C'
        when 'U'
          strand << 'A'
        when 'A'
          strand << 'T'
      end
    }
    strand
  end
end
