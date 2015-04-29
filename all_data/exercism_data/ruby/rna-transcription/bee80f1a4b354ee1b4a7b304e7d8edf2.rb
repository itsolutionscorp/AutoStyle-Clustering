class Complement
  def self.of_dna(dna_strand)
    complement = ''
    dna_strand.each_char do |nucleotide|
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
    return complement
  end

  def self.of_rna(rna_strand)
    complement = ''
    rna_strand.each_char do |nucleotide|
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
    return complement
  end

end
