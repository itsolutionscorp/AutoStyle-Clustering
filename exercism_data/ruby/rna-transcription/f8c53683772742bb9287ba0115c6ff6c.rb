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
    component.chars.map do |symbol|
      converter.fetch(symbol) { raise ArgumentError }
    end.join
  end
end
