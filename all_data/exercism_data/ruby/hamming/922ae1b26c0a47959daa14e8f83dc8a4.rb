class Hamming
  def self.compute(sequence_a, sequence_b)
    Strand.new(sequence_a) <=> Strand.new(sequence_b)
  end
end

class Strand < Array
  def initialize(sequence)
    sequence.each_char { |acid_type| self << Acid.new(acid_type) }
  end

  def <=>(other_strand)
    point_mutations = 0

    self.each_with_index do |acid, index|
      point_mutations += acid <=> other_strand[index]
    end

    point_mutations
  end
end

class Acid
  attr_reader :type

  def initialize(type)
    @type = type.capitalize
  end

  def <=>(other_acid)
    other_acid.nil? || @type == other_acid.type ? 0 : 1
  end
end
