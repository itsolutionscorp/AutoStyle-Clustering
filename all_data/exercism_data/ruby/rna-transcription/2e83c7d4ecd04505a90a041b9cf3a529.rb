class Complement
  INVALID_INPUT = 'Invalid input'
  def self.rewrite(char, type)
    case type
      when :dna
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
      when :rna
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
      else
        raise INVALID_INPUT
    end
  end

  def self.replace_each(string, type)
    result = []
    (0...string.length).step do |i|
      result[i] = rewrite(string[i], type)
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
