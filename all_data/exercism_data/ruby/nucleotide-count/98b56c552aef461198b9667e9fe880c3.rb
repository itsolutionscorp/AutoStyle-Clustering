class DNA
  def initialize(symbols)
    @sequence = build_sequence(symbols)
    raise ArgumentError unless valid_dna_sequence?
  end

  def count(symbol)
    nucleotide = make_nucleotide(symbol)
    sequence.count(nucleotide)
  end

  def nucleotide_counts
    counts = dna_symbols.map { |symbol| count(symbol) }
    Hash[dna_symbols.zip(counts)]
  end

  private
  attr_reader :sequence

  def build_sequence(symbols)
    symbols.chars.map { |symbol| make_nucleotide(symbol) }
  end

  def valid_dna_sequence?
    sequence.all?(&:valid_in_dna?)
  end

  def make_nucleotide(symbol)
    Nucleotide.new(symbol)
  end

  def dna_symbols
    Nucleotide::DNA_SYMBOLS
  end
end

class Nucleotide
  def initialize(symbol)
    @symbol = symbol
    raise ArgumentError unless valid_symbol?
  end

  def valid_in_dna?
    DNA_SYMBOLS.include?(symbol)
  end

  def ==(other)
    symbol == other.symbol
  end

  SYMBOLS = %w(A T C G U)
  DNA_SYMBOLS = SYMBOLS - %w(U)

  protected
  attr_reader :symbol

  def valid_symbol?
    SYMBOLS.include?(symbol)
  end
end
