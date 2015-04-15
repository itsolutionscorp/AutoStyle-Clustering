module Nucleobases
  module_function

  def all
    %w(A T C G U)
  end

  def dna
    all - %w(U)
  end
end

class DNA
  attr_reader :sequence

  def initialize(symbols)
    @sequence = Sequence.new(symbols)
    raise ArgumentError unless valid_sequence?
  end

  def count(symbol)
    sequence.count(symbol)
  end

  def nucleotide_counts
    Nucleobases.dna.each_with_object({}) do |symbol, counts|
      counts[symbol] = count(symbol)
    end
  end

  private
  def valid_sequence?
    sequence.nucleotides.all? { |n| valid_nucleotide?(n) }
  end

  def valid_nucleotide?(nucleotide)
    Nucleobases.dna.include?(nucleotide.symbol)
  end
end

class Sequence
  attr_reader :symbols

  def initialize(symbols)
    @symbols = symbols
  end

  def count(symbol)
    nucleotides.count(Nucleotide.new(symbol))
  end

  def nucleotides
    symbols.chars.map { |symbol| Nucleotide.new(symbol) }
  end
end

class Nucleotide
  attr_reader :symbol

  def initialize(symbol)
    @symbol = symbol
    raise ArgumentError unless valid_symbol?
  end

  def ==(other)
    symbol == other.symbol
  end

  protected
  def valid_symbol?
    Nucleobases.all.include?(symbol)
  end
end
