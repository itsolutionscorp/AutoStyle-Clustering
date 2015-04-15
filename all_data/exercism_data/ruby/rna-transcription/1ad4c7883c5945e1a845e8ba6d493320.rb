class Complement

  def self.of_dna(dna)
    rna = ''
    dna.each_char do |nucleotide|
      case nucleotide
      when 'G'
        rna += 'C'
      when 'C'
        rna += 'G'
      when 'T'
        rna += 'A'
      when 'A'
        rna += 'U'
      else
        raise "Not a valid DNA strand"
      end
    end
    rna
  end

  def self.of_rna(rna)
    dna = ''
    rna.each_char do |nucleotide|
      case nucleotide
      when 'G'
        dna += 'C'
      when 'C'
        dna += 'G'
      when 'A'
        dna += 'T'
      when 'U'
        dna += 'A'
      else
        raise "Not a valid RNA strand"
      end
    end
    dna
  end

end
