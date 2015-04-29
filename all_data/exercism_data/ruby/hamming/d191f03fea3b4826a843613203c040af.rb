class DNA
  def initialize(symbols)
    @symbols = symbols
  end

  def hamming_distance(symbols)
    other_sequence = Sequence.new(symbols)
    sequence.hamming_distance(other_sequence)
  end

  private
  attr_reader :symbols

  def sequence
    Sequence.new(symbols)
  end
end

class Sequence
  attr_reader :symbols

  def initialize(symbols)
    @symbols = symbols
  end

  def hamming_distance(other)
    nucleotide_pairs(other).count(&mutation_pair)
  end

  protected
  def nucleotide_pairs(other)
    nucleotides.zip(other.nucleotides).reject(&incomplete_pair)
  end

  def nucleotides
    symbols.chars.map { |symbol| Nucleotide.new(symbol) }
  end

  def mutation_pair
    ->(pair) { pair[0] != pair[1] }
  end

  def incomplete_pair
    ->(pair) { pair.any?(&:nil?) }
  end
end

class Nucleotide
  attr_reader :symbol

  def initialize(symbol)
    @symbol = symbol
  end

  def ==(other)
    symbol == other.symbol
  end
end
