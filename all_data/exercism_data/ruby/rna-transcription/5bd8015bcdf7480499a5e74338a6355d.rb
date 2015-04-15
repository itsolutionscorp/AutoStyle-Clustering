class Complement
  OF_DNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  OF_RSA = OF_DNA.invert

  def self.of_dna(component)
    encode(component, OF_DNA)
  end

  def self.of_rna(component)
    encode(component, OF_RSA)
  end

  private

  def self.encode(component, converter)
    case component.size
    when 1 then do_encode(component, converter)
    else
      component.chars.map { |symbol| do_encode(symbol, converter) }.join('')
    end
  end

  def self.do_encode(component, converter)
    converter.fetch(component) { raise ArgumentError }
  end
end
