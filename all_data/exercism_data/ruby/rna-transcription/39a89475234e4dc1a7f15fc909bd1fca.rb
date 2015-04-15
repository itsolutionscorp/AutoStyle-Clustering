class Complement

  def self.of_dna(component)
    do_encode(component, self.method(:encode_of_dna))
  end

  def self.of_rna(component)
    do_encode(component, self.method(:encode_of_rna))
  end

  private

  def self.do_encode(component, encoder)
    case component.size
    when 1 then encoder.call(component)
    else
      component.chars.map { |symbol| encoder.call(symbol) }.join('')
    end
  end

  def self.encode_of_dna(symbol)
    case symbol
      when "G" then 'C'
      when "C" then 'G'
      when "T" then 'A'
      when "A" then 'U'
      else
        raise ArgumentError
    end
  end

  def self.encode_of_rna(symbol)
    case symbol
      when "G" then 'C'
      when "C" then 'G'
      when "A" then 'T'
      when "U" then 'A'
      else
        raise ArgumentError
    end
  end
end
