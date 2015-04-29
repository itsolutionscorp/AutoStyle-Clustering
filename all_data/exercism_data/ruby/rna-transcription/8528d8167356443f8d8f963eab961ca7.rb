class Complement
  def self.of_dna(dna)
    result = []
    dna.split('').each do |letter|
      case letter
      when 'C'
        result << 'G'
      when 'G'
        result << 'C'
      when 'T'
        result << 'A'
      when 'A'
        result << 'U'
      end
    end
    result.join
  end

  def self.of_rna(dna)
    result = []
    dna.split('').each do |letter|
      case letter
      when 'G'
        result << 'C'
      when 'C'
        result << 'G'
      when 'U'
        result << 'A'
      when 'A'
        result << 'T'
      end
    end
    result.join
  end
end
