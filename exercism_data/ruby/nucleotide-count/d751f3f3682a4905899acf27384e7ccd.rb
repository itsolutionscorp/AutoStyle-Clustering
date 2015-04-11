class DNA
  attr_reader :nucleotides

  def initialize(strand)
    @nucleotides = strand.chars.map do |symbol| 
      Nucleotide.new(symbol)
    end
  end

  def nucleotide_counts
    {
      Nucleotide::ADENINE  => count(Nucleotide::ADENINE),
      Nucleotide::CYTOSINE => count(Nucleotide::CYTOSINE),
      Nucleotide::GUANINE  => count(Nucleotide::GUANINE),
      Nucleotide::THYMINE  => count(Nucleotide::THYMINE),
    }
  end

  def count(symbol)
    nucleobase = Nucleotide.new(symbol)
    nucleotides.count(nucleobase)
  end
  
end

class Nucleotide
  attr_reader :nucleobase

  GUANINE  = 'G'
  ADENINE  = 'A'
  THYMINE  = 'T'
  CYTOSINE = 'C'
  URACIL   = 'U'

  def initialize(symbol)
    unless nucleotide_symbol?(symbol)
      raise ArgumentError, "#{symbol} is not a nucleotide symbol"
    end
    @nucleobase = symbol
  end

  def nucleotide_symbol?(symbol)
    [GUANINE, ADENINE, THYMINE, CYTOSINE, URACIL].include?(symbol)
  end

  def ==(other)
    nucleobase == other.nucleobase
  end
end
