class Complement

  def self.of_dna(nucleotides)
    dna_complement_array = nucleotides.chars.map do |nucleotide|
      case nucleotide
      when 'C' then 'G'
      when 'G' then 'C'
      when 'T' then 'A'
      when 'A' then 'U'
      end
    end
    return dna_complement_array.join
  end

  def self.of_rna(nucleotides)
    rna_complement_array = nucleotides.chars.map do |nucleotide|
      case nucleotide
      when 'C' then 'G'
      when 'G' then 'C'
      when 'U' then 'A'
      when 'A' then 'T'
      end
    end
    return rna_complement_array.join
  end

end
