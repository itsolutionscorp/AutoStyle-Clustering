class Complement
  def self.of_dna(strand)
    complement = ''
    strand.each_char do |nucleotide|
      case nucleotide
      when 'C'
        complement += 'G'
      when 'G'
        complement += 'C'
      when 'T'
        complement += 'A'
      when 'A'
        complement += 'U'
      end
    end

    complement
  end

  def self.of_rna(strand)
    complement = ''
    strand.each_char do |nucleotide|
      case nucleotide
      when 'C'
        complement += 'G'
      when 'G'
        complement += 'C'
      when 'U'
        complement += 'A'
      when 'A'
        complement += 'T'
      end
    end

    complement
  end

end
