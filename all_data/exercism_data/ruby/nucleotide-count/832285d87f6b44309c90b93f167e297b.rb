class DNA
  DNA_NUCLEOTIDES = %w(A T C G)

  def initialize(strand)
    @strand = strand
    raise ArgumentError unless dna?
  end

  def count(symbol)
    nucleotide = Nucleotide.new(symbol)
    @strand.count(nucleotide.symbol)
  end

  def nucleotide_counts
    DNA_NUCLEOTIDES.each_with_object(Hash.new(0)) do |symbol, counts|
      counts[symbol] = count(symbol)
    end 
  end

private

  def dna?
    (@strand.chars - DNA_NUCLEOTIDES).empty?
  end
end

class Nucleotide
  NUCLEOTIDES     = %w(A T C G U)
  attr_reader :symbol

  def initialize(symbol)
    @symbol = symbol
    raise ArgumentError unless nucleotide?
  end

  def nucleotide?
    NUCLEOTIDES.include?(@symbol)
  end
end
