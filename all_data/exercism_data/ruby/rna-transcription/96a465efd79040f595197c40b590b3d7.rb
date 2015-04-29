class Complement

  def self.of_dna(strand)
    result = ''
    strand.chars.each do |base|
      case base
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
    result
  end

  def self.of_rna(strand)
    result = ''
    strand.chars.each do |base|
      case base
      when 'C'
        result << 'G'
      when 'G'
        result << 'C'
      when 'U'
        result << 'A'
      when 'A'
        result << 'T'
      end
    end
    result
  end

end
