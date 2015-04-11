class Complement
  def self.of_dna strand
    rna = ""

    for i in 0..strand.length
      case strand[i]
      when 'G'
        rna << 'C'
      when 'C'
        rna << 'G'
      when 'T'
        rna << 'A'
      when 'A'
        rna << 'U'
      end
    end

    rna
  end

  def self.of_rna strand
    dna = ""

    for i in 0..strand.length
      case strand[i]
      when 'G'
        dna << 'C'
      when 'C'
        dna << 'G'
      when 'U'
        dna << 'A'
      when 'A'
        dna << 'T'
      end
    end

    dna
  end
end
