class Complement

  def self.of_dna(rna_strand)
    complement_strand = ""

    rna_strand.each_char do |nucleotide|
      case nucleotide
      when 'G'
        complement_strand += 'C'
      when 'C'
        complement_strand += 'G'
      when 'T'
        complement_strand += 'A'
      when 'A'
        complement_strand += 'U'
      end
    end
      return complement_strand
  end

  def self.of_rna(dna_strand)
    complement_strand = ""

    dna_strand.each_char do |nucleotide|

      case nucleotide
      when 'C'
        complement_strand += 'G'
      when 'G'
        complement_strand += 'C'
      when 'A'
        complement_strand += 'T'
      when 'U'
        complement_strand += 'A'
      end
    end
      return complement_strand
  end

end
