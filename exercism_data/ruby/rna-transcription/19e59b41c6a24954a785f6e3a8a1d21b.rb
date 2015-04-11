class Mapping
  attr_reader :rule

  def initialize(rule)
    @rule = rule
  end

  def apply_to_string string
    apply_to_enum(string.chars).join('')
  end

  def apply_to_enum enum
    enum.map(&method(:apply_to_object))
  end

  def apply_to_object object
    rule.fetch(object) do
      fail(ArgumentError.new "Invalid element #{object}, exepected values are #{rule.keys}")
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

  def self.of_rna(strand)
    rna_to_dna_mapping.apply_to_string(strand)
  end

  def self.of_dna(strand)
    dna_to_rna_mapping.apply_to_string(strand)
  end
end
