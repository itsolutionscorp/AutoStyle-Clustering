class Mapping
  attr_reader :rule

  def initialize(rule)
    @rule = rule
  end

  def apply_to_string string
    appy_to_enum(string.chars).join('')
  end

  def apply_to_enum enum
    enum.map do |element|
      if rule.key?(element)
        rule[element]
      else
        fail(ArgumentError.new "Invalid element #{element}, exepected values are #{rule.keys}")
      end
    end
  end

  def invert
    self.class.new(rule.invert)
  end
end

class Complement
  def self.dna_to_rna_mapping
    Mapping.new('C' => 'G',
                'G' => 'C',
                'T' => 'A',
                'A' => 'U')
  end

  def self.rna_to_dna_mapping
    dna_to_rna_mapping.invert
  end

  def self.of_rna(string)
    rna_to_dna_mapping.apply_to_string(string)
  end

  def self.of_dna(string)
    dna_to_rna_mapping.apply_to_string(string)
  end
end
