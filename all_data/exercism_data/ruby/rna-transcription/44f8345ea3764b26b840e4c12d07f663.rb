class Complement
  def self.of_dna(dna_strand)
    rna_complement = ''
    dna_strand.each_char do |nucleotide|
      case nucleotide
      when 'C'
        rna_complement << 'G'
      when 'G'
        rna_complement << 'C'
      when 'T'
        rna_complement << 'A'
      when 'A'
        rna_complement << 'U'
      end
    end
    rna_complement
  end

  def self.of_rna(rna, dna = '')
    return dna if rna == ''
    case rna.slice!(0)
      when 'C'
        dna << 'G'
      when 'G'
        dna << 'C'
      when 'A'
        dna << 'T'
      when 'U'
        dna << 'A'
      end
    self.of_rna(rna, dna)
  end
end
