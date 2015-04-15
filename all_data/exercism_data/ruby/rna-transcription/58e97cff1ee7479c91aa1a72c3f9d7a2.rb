module Complement
  module_function

  of_dna = {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
  }
  of_rna = of_dna.invert
  CONVERTERS = { 'of_dna' => of_dna, 'of_rna' => of_rna }

  %w( of_dna of_rna).each do |conversion|
    define_method conversion do |component|
      encode(component, CONVERTERS[conversion])
    end
  end

  def encode(component, converter)
    component.chars.map do |symbol|
      converter.fetch(symbol) { raise ArgumentError }
    end.join
  end
  private_class_method :encode
end
