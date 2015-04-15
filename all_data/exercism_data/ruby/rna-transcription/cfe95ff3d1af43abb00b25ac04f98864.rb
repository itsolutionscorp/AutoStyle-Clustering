class Complement

  def self.of_dna(nucleotides)
    complements = nucleotides.chars.map do |nucleotide|
      Complement.transcribe(nucleotide, 'dna')
    end
    return complements.join('')
  end

  def self.of_rna(nucleotides)
    complements = nucleotides.chars.map do |nucleotide|
      Complement.transcribe(nucleotide, 'rna')
    end
    return complements.join('')
  end

  def self.transcribe(nucleotide, type)
    if type == 'dna'
      n1 = 'T'
      n2 = 'U'
    elsif type == 'rna'
      n1 = 'U'
      n2 = 'T'
    end
    case nucleotide
      when 'C'
        'G'
      when 'G'
        'C'
      when n1
        'A'
      when 'A'
        n2
    end
  end

end
