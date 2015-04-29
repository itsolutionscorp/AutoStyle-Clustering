class Complement
  def self.of_dna(string)
    for i in 0..string.length - 1
      case string[i]
      when 'G'
        string[i] = 'C'
      when 'C'
        string[i] = 'G'
      when 'T'
        string[i] = 'A'
      when 'A'
        string[i] = 'U'
      else
        raise ArgumentError, 'Input string contains more characters than the four found in RNA'
      end
    end
    return string
  end

  def self.of_rna(string)
    for i in 0..string.length - 1
      case string[i]
      when 'G'
        string[i] = 'C'
      when 'C'
        string[i] = 'G'
      when 'A'
        string[i] = 'T'
      when 'U'
        string[i] = 'A'
      else
        raise ArgumentError, 'Input string contains more characters than the four found in RNA'
      end
    end
    return string
  end

end
  
