class DNA
  NUCLEOTIDES = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}.freeze
  VALID_DNA_SYMBOLS = ['A','C','G','T'].freeze
  VALID_NUCLEOTIDES = ['A','C','G','T','U'].freeze
  INVALID_DNA_SYMBOL_ERROR = 'DNA instance only accepts valid DNA symbols (A,C,G,T)'.freeze
  INVALID_NUCLEOTIDE_ERROR = 'Only valid nucleotides can be counted (A,C,G,T,U)'.freeze

  def initialize(symbols)
    self.symbols = symbols
    raise ArgumentError.new(INVALID_DNA_SYMBOL_ERROR) unless valid_dna_symbols?
  end

  def count(nucleotide)
    raise ArgumentError.new(INVALID_NUCLEOTIDE_ERROR) unless valid_nucleotide?(nucleotide)
    nucleotide_counts[nucleotide] ||= 0
  end

  def nucleotide_counts
    symbols.chars.to_a.each.with_object(NUCLEOTIDES.dup) do |symbol, nucleotides|
      nucleotides[symbol] += 1
    end
  end

  private

  def valid_nucleotide?(nucleotide)
    VALID_NUCLEOTIDES.include?(nucleotide)
  end

  def valid_dna_symbols?
    return (symbols.chars.to_a - VALID_DNA_SYMBOLS).empty?
  end

  attr_accessor :symbols
end
