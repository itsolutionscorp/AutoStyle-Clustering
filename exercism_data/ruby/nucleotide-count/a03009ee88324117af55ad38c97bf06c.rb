class Nucleotides
  attr_reader :symbol

  VALID_NUCLEOTIDES = ['A','T','C','G','U']

  def initialize(symbol)
    @symbol = symbol
    raise ArgumentError unless valid
  end

  def valid
    VALID_NUCLEOTIDES.include? symbol
  end

end

class DNA
  DNA_NUCLEOTIDES = ['A','T','C','G']

  attr_reader :strand

  def initialize(strand)
    @strand = strand
    raise ArgumentError unless dna?
  end

  def count(symbol)
    Nucleotides.new(symbol)
    strand.count(symbol)
  end

  def nucleotide_counts
    DNA_NUCLEOTIDES.each_with_object(Hash.new(0)) do |symbol, nc|
      nc[symbol] = count(symbol)
    end
  end

  private

  def dna?
    strand.chars.all? {|char| DNA_NUCLEOTIDES.include? char }
  end

end
