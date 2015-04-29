class Complement

  def self.of_dna(rna_strand)
    complement = ''
    rna_strand.each_char { |x| complement << find_dna_complement_of(x) }
    complement
  end

  def self.of_rna(dna_strand)
    complement = ''
    dna_strand.each_char { |x| complement << find_rna_complement_of(x) }
    complement
  end

  def self.find_dna_complement_of(x)
    case x
    when 'C'
      'G'
    when 'G'
      'C'
    when 'T'
      'A'
    when 'A'
      'U'
    when 'U'
      raise ArgumentError.new('Invalid nucleotide')
    end

  end

  def self.find_rna_complement_of(x)
    case x
    when 'G'
      'C'
    when 'C'
      'G'
    when 'A'
      'T'
    when 'U'
      'A'
    when 'T'
      raise ArgumentError.new('Invalid nucleotide')
    end
  end
end
