class Complement
  def self.of_dna(dna_strand)
    rna = ''

    dna_strand.each_char { |base|
      if base == 'G'
        rna << 'C'
      elsif base == 'C'
        rna << 'G'
      elsif base == 'T'
        rna << 'A'
      elsif base == 'A'
        rna << 'U'
      end
    }
    rna
  end

  def self.of_rna(rna_strand)
    dna = ''

    rna_strand.each_char { |base|
      if base == 'G'
        dna << 'C'
      elsif base == 'C'
        dna << 'G'
      elsif base == 'U'
        dna << 'A'
      elsif base == 'A'
        dna << 'T'
      end
    }
    dna
  end
end
