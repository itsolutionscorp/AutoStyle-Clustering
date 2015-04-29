class Complement
  INVALID_INPUT = 'Invalid input'
  def self.dna_to_rna(char)
    case char
      when 'G'
        return 'C'
      when 'C'
        return 'G'
      when 'T'
        return 'A'
      when 'A'
        return 'U'
      else
        raise INVALID_INPUT
    end
  end

  def self.rna_to_dna(char)
    case char
      when 'C'
        return 'G'
      when 'G'
        return 'C'
      when 'A'
        return 'T'
      when 'U'
        return 'A'
      else
        raise INVALID_INPUT
    end
  end

  def self.replace_each(string, type)
    result = []
    case type
      when :dna
        (0...string.length).step do |i|
          result[i] = dna_to_rna(string[i])
        end
      when :rna
        (0...string.length).step do |i|
          result[i] = rna_to_dna((string[i]))
        end
      else
        raise INVALID_INPUT
    end
    result.join
  end

  def self.of_dna(dna)
    replace_each(dna, :dna)
  end

  def self.of_rna(rna)
    replace_each(rna, :rna)
  end
end
