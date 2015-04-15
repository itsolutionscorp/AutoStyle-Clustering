class Complement

  def self.of_dna(nucleotide)
    compliment = []
    nucleotide.each_char { |x|
      case x
      when 'G'
        compliment << 'C'
      when 'C'
        compliment << 'G'
      when 'T'
        compliment << 'A'
      when 'A'
        compliment << 'U'
      end
    }
    return compliment.join
  end

  def self.of_rna(nucleotide)
    compliment = []
    nucleotide.each_char { |x|
      case x
      when 'C'
        compliment << 'G'
      when 'G'
        compliment << 'C'
      when 'U'
        compliment << 'A'
      when 'A'
        compliment << 'T'
      end
    }
    return compliment.join
  end

end
