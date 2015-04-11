class Complement
  def self.of_dna string
    i = 0
    while i < string.length
      case string[i]
      when 'A'
        string[i] = 'U'
      when 'G'
        string[i] = 'C'
      when 'C'
        string[i] = 'G'
      when 'T'
        string[i] = 'A'
      end
      i+=1
    end
    return string
  end

    def self.of_rna string
    i = 0
    while i < string.length
      case string[i]
      when 'C'
        string[i] = 'G'
      when 'G'
        string[i] = 'C'
      when 'A'
        string[i] = 'T'
      when 'U'
        string[i] = 'A'
      end
      i+=1
    end
    return string
  end
end
