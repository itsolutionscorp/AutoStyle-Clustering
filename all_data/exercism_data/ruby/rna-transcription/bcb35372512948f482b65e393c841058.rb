class Complement

  def self.of_dna(dna_string)
    rna_string = ''
    dna_string.each_char do |nucleotide|
      case nucleotide
      when 'G' then rna_string << 'C'
      when 'C' then rna_string << 'G'
      when 'T' then rna_string << 'A'
      when 'A' then rna_string << 'U'
      end
    end
    rna_string
  end

  def self.of_rna(rna_string)
    dna_string = ''
    rna_string.each_char do |nucleotide|
      case nucleotide
      when 'C' then dna_string << 'G'
      when 'G' then dna_string << 'C'
      when 'A' then dna_string << 'T'
      when 'U' then dna_string << 'A'
      end
    end
    dna_string
  end

end
