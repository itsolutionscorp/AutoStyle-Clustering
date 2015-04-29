class DNA
  
  VALID_NUCLEOTIDES = %w(A T C G U)
  DNA_NUCLEOTIDES = %w(A T C G)

  attr_reader :strand

  def initialize(strand)
    @strand = strand
    raise ArgumentError unless dna?
  end

  def count(symbol)
    raise ArgumentError unless valid?(symbol)
    strand.count(symbol)
  end

  def nucleotide_counts
    DNA_NUCLEOTIDES.each_with_object(Hash.new(0)) do |symbol, nc|
      nc[symbol] = count(symbol)
    end
  end

  private

  def valid?(symbol)
    VALID_NUCLEOTIDES.include? symbol
  end

  def dna?
    strand.chars.all? {|char| DNA_NUCLEOTIDES.include? char }
  end

end
