class Complement
  def self.of_dna(nuc_str)
    nuc_str.each_char.map { |ch| match_dna(ch) }.join('')
  end

  def self.of_rna(nuc_str)
    nuc_str.each_char.map { |ch| match_rna(ch) }.join('')
  end

  def self.match_dna(nuc)
    case nuc
    when 'A'
      'U'
    when 'C'
      'G'
    when 'G'
      'C'
    when 'T'
      'A'
    end
  end

  def self.match_rna(nuc)
    case nuc
    when 'U'
      'A'
    when 'C'
      'G'
    when 'G'
      'C'
    when 'A'
      'T'
    end
  end
end
