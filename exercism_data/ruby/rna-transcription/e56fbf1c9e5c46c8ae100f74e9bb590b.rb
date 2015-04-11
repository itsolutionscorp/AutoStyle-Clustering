class Complement
  def self.of_dna(str)
    str.chars.map { |char| convert_dna_from(char) }.join('')
  end

  def self.of_rna(str)
    str.chars.map { |char| convert_rna_from(char) }.join('')
  end

  private
  def self.convert_dna_from(char)
    case char
    when 'C'
      'G'
    when 'G'
      'C'
    when 'T'
      'A'
    when 'A'
      'U'
    end
  end

  def self.convert_rna_from(char)
    case char
    when 'C'
      'G'
    when 'G'
      'C'
    when 'U'
      'A'
    when 'A'
      'T'
    end
  end

end
